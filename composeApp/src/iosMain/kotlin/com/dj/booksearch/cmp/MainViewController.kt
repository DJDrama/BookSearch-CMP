package com.dj.booksearch.cmp

import androidx.compose.ui.window.ComposeUIViewController
import com.dj.booksearch.app.App
import com.dj.booksearch.cmp.di.initKoin

fun MainViewController() = ComposeUIViewController(
    configure = {
        initKoin()
    }
) {
    App()
}