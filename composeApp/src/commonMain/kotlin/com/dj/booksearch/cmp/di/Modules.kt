package com.dj.booksearch.cmp.di

import com.dj.booksearch.cmp.book.data.network.KtorRemoteBookDataSource
import com.dj.booksearch.cmp.book.data.network.RemoteBookDataSource
import com.dj.booksearch.cmp.book.data.repository.DefaultBookRepository
import com.dj.booksearch.cmp.book.domain.repository.BookRepository
import com.dj.booksearch.cmp.book.presentation.SelectedBookViewModel
import com.dj.booksearch.cmp.book.presentation.book_detail.BookDetailViewModel
import com.dj.booksearch.cmp.book.presentation.book_list.BookListViewModel
import com.dj.booksearch.cmp.core.data.HttpClientFactory
import org.koin.core.module.Module
import org.koin.core.module.dsl.singleOf
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.bind
import org.koin.dsl.module

expect val platformModule: Module

val sharedModule = module {
    single {
        HttpClientFactory.create(engine = get())
    }
    singleOf(::KtorRemoteBookDataSource).bind<RemoteBookDataSource>()
    singleOf(::DefaultBookRepository).bind<BookRepository>()
    viewModelOf(::BookListViewModel)
    viewModelOf(::SelectedBookViewModel)
    viewModelOf(::BookDetailViewModel)
}