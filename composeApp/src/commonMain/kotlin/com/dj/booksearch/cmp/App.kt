package com.dj.booksearch.cmp

import androidx.compose.runtime.Composable
import com.dj.booksearch.cmp.book.presentation.book_list.BookListScreenRoot
import com.dj.booksearch.cmp.book.presentation.book_list.BookListViewModel
import org.jetbrains.compose.ui.tooling.preview.Preview
import org.koin.compose.viewmodel.koinViewModel

@Composable
@Preview
fun App() {
    val bookListViewModel = koinViewModel<BookListViewModel>()
    BookListScreenRoot(
        viewModel = bookListViewModel,
        onBookClick = {

        }
    )
}