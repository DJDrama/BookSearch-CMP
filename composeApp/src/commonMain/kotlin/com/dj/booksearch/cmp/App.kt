package com.dj.booksearch.cmp

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import com.dj.booksearch.cmp.book.data.network.KtorRemoteBookDataSource
import com.dj.booksearch.cmp.book.data.repository.DefaultBookRepository
import com.dj.booksearch.cmp.book.domain.repository.BookRepository
import com.dj.booksearch.cmp.book.presentation.book_list.BookListScreenRoot
import com.dj.booksearch.cmp.book.presentation.book_list.BookListViewModel
import com.dj.booksearch.cmp.core.data.HttpClientFactory
import io.ktor.client.engine.HttpClientEngine
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
@Preview
fun App(engine: HttpClientEngine) {
    BookListScreenRoot(
        viewModel = remember {
            BookListViewModel(
                bookRepository = DefaultBookRepository(
                    remoteBookDataSource = KtorRemoteBookDataSource(
                        httpClient = HttpClientFactory.create(engine = engine)
                    )
                )
            )
        },
        onBookClick = {

        }
    )
}