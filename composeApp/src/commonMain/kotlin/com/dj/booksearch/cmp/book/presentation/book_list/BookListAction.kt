package com.dj.booksearch.cmp.book.presentation.book_list

import com.dj.booksearch.cmp.book.domain.Book

sealed interface BookListAction {
    data class OnSearchQueryChange(val query: String): BookListAction
    data class OnBookClick(val book: Book): BookListAction
    data class OnTabSelected(val index: Int): BookListAction
}