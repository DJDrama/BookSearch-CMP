package com.dj.booksearch.cmp.book.domain.repository

import com.dj.booksearch.cmp.book.domain.Book
import com.dj.booksearch.cmp.core.domain.DataError
import com.dj.booksearch.cmp.core.domain.EmptyResult
import com.dj.booksearch.cmp.core.domain.Result
import kotlinx.coroutines.flow.Flow

interface BookRepository {
    suspend fun searchBooks(query: String): Result<List<Book>, DataError.Remote>
    suspend fun getBookDescription(bookId: String): Result<String?, DataError>

    fun getFavoriteBooks(): Flow<List<Book>>
    fun isBookFavorite(id: String): Flow<Boolean>
    suspend fun markAsFavorite(book: Book): EmptyResult<DataError.Local>
    suspend fun deleteFromFavorites(id: String)
}