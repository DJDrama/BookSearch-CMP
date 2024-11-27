package com.dj.booksearch.cmp

import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import com.dj.booksearch.app.App
import com.dj.booksearch.cmp.di.initKoin

fun main() {
    initKoin()
    application {
        Window(
            onCloseRequest = ::exitApplication,
            title = "BookSearch-CMP",
        ) {
            App()
        }
    }
}