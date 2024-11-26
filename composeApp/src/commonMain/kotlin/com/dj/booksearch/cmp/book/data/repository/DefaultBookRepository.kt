package com.dj.booksearch.cmp.book.data.repository

import com.dj.booksearch.cmp.book.data.mappers.toBook
import com.dj.booksearch.cmp.book.data.network.RemoteBookDataSource
import com.dj.booksearch.cmp.book.domain.Book
import com.dj.booksearch.cmp.book.domain.repository.BookRepository
import com.dj.booksearch.cmp.core.domain.DataError
import com.dj.booksearch.cmp.core.domain.Result
import com.dj.booksearch.cmp.core.domain.map

class DefaultBookRepository(
    private val remoteBookDataSource: RemoteBookDataSource
) : BookRepository {
    override suspend fun searchBooks(query: String): Result<List<Book>, DataError.Remote> {
        return remoteBookDataSource.searchBooks(query = query).map { dto ->
            dto.results.map {
                it.toBook()
            }
        }
    }
}