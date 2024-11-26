package com.dj.booksearch.cmp.book.data.network

import com.dj.booksearch.cmp.book.data.dto.SearchResponseDto
import com.dj.booksearch.cmp.core.domain.DataError
import com.dj.booksearch.cmp.core.domain.Result

interface RemoteBookDataSource {
    suspend fun searchBooks(
        query: String,
        resultLimit: Int? = null
    ): Result<SearchResponseDto, DataError.Remote>
}