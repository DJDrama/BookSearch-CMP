package com.dj.booksearch.cmp.book.presentation.book_list

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class BookListViewModel : ViewModel() {

    private val _state = MutableStateFlow(value = BookListState())
    val state = _state.asStateFlow()

    fun onAction(action: BookListAction) {
        when (action) {

            is BookListAction.OnBookClick -> {
                _state.update {
                    it.copy(
                    )
                }
            }

            is BookListAction.OnSearchQueryChange -> {
                _state.update {
                    it.copy(
                        searchQuery = action.query
                    )
                }
            }


            is BookListAction.OnTabSelected -> {
                _state.update {
                    it.copy(
                        selectedTabIndex = action.index
                    )
                }
            }
        }
    }
}