package com.dj.booksearch.cmp.book.domain.repository

import com.dj.booksearch.cmp.book.domain.Book
import com.dj.booksearch.cmp.core.domain.DataError
import com.dj.booksearch.cmp.core.domain.Result

interface BookRepository {
    suspend fun searchBooks(query: String): Result<List<Book>, DataError.Remote>
}