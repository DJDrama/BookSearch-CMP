package com.dj.booksearch.cmp.book.presentation.book_list

import com.dj.booksearch.cmp.book.domain.Book
import com.dj.booksearch.cmp.core.presentation.UiText

data class BookListState(
    val searchQuery: String = "Kotlin",
    val searchResults: List<Book> = emptyList(),
    val favoriteBooks: List<Book> = emptyList(),
    val isLoading: Boolean = false,
    val selectedTabIndex: Int = 0,
    val errorMessage: UiText? = null
)