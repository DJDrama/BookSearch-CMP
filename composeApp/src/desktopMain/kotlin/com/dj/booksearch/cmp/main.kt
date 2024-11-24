package com.dj.booksearch.cmp

import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application

fun main() = application {
    Window(
        onCloseRequest = ::exitApplication,
        title = "BookSearch-CMP",
    ) {
        App()
    }
}