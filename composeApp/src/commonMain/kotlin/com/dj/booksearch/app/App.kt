package com.dj.booksearch.app

import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.lifecycle.ViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import androidx.navigation.compose.rememberNavController
import com.dj.booksearch.cmp.book.presentation.SelectedBookViewModel
import com.dj.booksearch.cmp.book.presentation.book_detail.BookDetailAction
import com.dj.booksearch.cmp.book.presentation.book_detail.BookDetailScreenRoot
import com.dj.booksearch.cmp.book.presentation.book_detail.BookDetailViewModel
import com.dj.booksearch.cmp.book.presentation.book_list.BookListScreenRoot
import com.dj.booksearch.cmp.book.presentation.book_list.BookListViewModel
import org.jetbrains.compose.ui.tooling.preview.Preview
import org.koin.compose.viewmodel.koinViewModel

@Composable
@Preview
fun App() {
    MaterialTheme {
        val navController = rememberNavController()
        NavHost(
            navController = navController,
            startDestination = Route.BookGraph,
        ) {
            navigation<Route.BookGraph>(
                startDestination = Route.BookList
            ) {
                composable<Route.BookList>(
                    exitTransition = {
                        slideOutHorizontally()
                    },
                    popEnterTransition = { // sliding in
                        slideInHorizontally()
                    }
                ) {
                    val bookListViewModel = koinViewModel<BookListViewModel>()
                    val selectedBookViewModel =
                        it.sharedKoinViewModel<SelectedBookViewModel>(navController = navController)

                    // Back to this book list screen, set null for selected book
                    LaunchedEffect(true) {
                        selectedBookViewModel.onSelectBook(null)
                    }

                    BookListScreenRoot(
                        viewModel = bookListViewModel,
                        onBookClick = { book ->
                            selectedBookViewModel.onSelectBook(book = book)
                            navController.navigate(route = Route.BookDetail(id = book.id))
                        }
                    )
                }
                composable<Route.BookDetail>(
                    enterTransition = {
                        slideInHorizontally { initialOffset ->
                            initialOffset
                        }
                    },
                    exitTransition = {
                        slideOutHorizontally { initialOffset ->
                            initialOffset
                        }
                    }
                ) {
                    val selectedBookViewModel =
                        it.sharedKoinViewModel<SelectedBookViewModel>(navController = navController)
                    val selectedBook by selectedBookViewModel.selectedBook.collectAsStateWithLifecycle()
                    val viewModel = koinViewModel<BookDetailViewModel>()
                    LaunchedEffect(selectedBook) {
                        selectedBook?.let { book ->
                            viewModel.onAction(action = BookDetailAction.OnSelectedBookChange(book = book))
                        }
                    }
                    BookDetailScreenRoot(
                        viewModel = viewModel,
                        onBackClick = {
                            navController.navigateUp()
                        }
                    )
                }
            }
        }

    }
}

@Composable
private inline fun <reified T : ViewModel> NavBackStackEntry.sharedKoinViewModel(
    navController: NavController
): T {
    val navGraphRoute = destination.parent?.route ?: return koinViewModel<T>()
    val parentEntry = remember(this) {
        navController.getBackStackEntry(navGraphRoute)
    }

    return koinViewModel(
        viewModelStoreOwner = parentEntry
    )
}
