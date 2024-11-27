package com.dj.booksearch.cmp.book.presentation.book_detail

import com.dj.booksearch.cmp.book.domain.Book

data class BookDetailState(
    val isLoading: Boolean = true,
    val isFavorite: Boolean = false,
    val book: Book? = null
)