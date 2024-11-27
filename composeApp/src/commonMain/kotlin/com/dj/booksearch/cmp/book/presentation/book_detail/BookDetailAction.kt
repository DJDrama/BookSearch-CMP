package com.dj.booksearch.cmp.book.presentation.book_detail

import com.dj.booksearch.cmp.book.domain.Book

sealed interface BookDetailAction {
    data object OnBackClick : BookDetailAction
    data object OnFavoriteClick : BookDetailAction
    data class OnSelectedBookChange(val book: Book) : BookDetailAction
}