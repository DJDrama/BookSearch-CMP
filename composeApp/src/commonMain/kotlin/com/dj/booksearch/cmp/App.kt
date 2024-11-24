package com.dj.booksearch.cmp

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import com.dj.booksearch.cmp.book.presentation.book_list.BookListScreenRoot
import com.dj.booksearch.cmp.book.presentation.book_list.BookListViewModel
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
@Preview
fun App() {
    BookListScreenRoot(
        viewModel = remember { BookListViewModel() },
        onBookClick = {

        }
    )
}