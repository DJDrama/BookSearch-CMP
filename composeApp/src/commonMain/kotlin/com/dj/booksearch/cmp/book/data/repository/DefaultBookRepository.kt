package com.dj.booksearch.cmp.book.data.repository

import androidx.sqlite.SQLiteException
import com.dj.booksearch.cmp.book.data.database.FavoriteBookDao
import com.dj.booksearch.cmp.book.data.mappers.toBook
import com.dj.booksearch.cmp.book.data.mappers.toBookEntity
import com.dj.booksearch.cmp.book.data.network.RemoteBookDataSource
import com.dj.booksearch.cmp.book.domain.Book
import com.dj.booksearch.cmp.book.domain.repository.BookRepository
import com.dj.booksearch.cmp.core.domain.DataError
import com.dj.booksearch.cmp.core.domain.EmptyResult
import com.dj.booksearch.cmp.core.domain.Result
import com.dj.booksearch.cmp.core.domain.map
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class DefaultBookRepository(
    private val remoteBookDataSource: RemoteBookDataSource,
    private val favoriteBookDao: FavoriteBookDao // dao can act as a datasource
) : BookRepository {
    override suspend fun searchBooks(query: String): Result<List<Book>, DataError.Remote> {
        return remoteBookDataSource.searchBooks(query = query).map { dto ->
            dto.results.map {
                it.toBook()
            }
        }
    }

    override suspend fun getBookDescription(bookId: String): Result<String?, DataError> {
        val localResult = favoriteBookDao.getFavoriteBook(id = bookId)
        return if (localResult == null) {
            remoteBookDataSource.getBookDetails(bookId).map { dto ->
                dto.description
            }
        } else {
            Result.Success(data = localResult.description)
        }
    }

    override fun getFavoriteBooks(): Flow<List<Book>> {
        return favoriteBookDao.getFavoriteBooks().map {
            it.map { bookEntity ->
                bookEntity.toBook()
            }
        }
    }

    override fun isBookFavorite(id: String): Flow<Boolean> {
        return favoriteBookDao.getFavoriteBooks().map { entities ->
            entities.any { it.id == id }
        }
    }

    override suspend fun markAsFavorite(book: Book): EmptyResult<DataError.Local> {
        return try {
            favoriteBookDao.upsert(book.toBookEntity())
            Result.Success(Unit)
        } catch (e: SQLiteException) {
            Result.Error(DataError.Local.DISK_FULL)
        }
    }

    override suspend fun deleteFromFavorites(id: String) {
        favoriteBookDao.deleteFavoriteBook(id = id)
    }

}