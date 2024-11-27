package com.dj.booksearch.cmp

import android.app.Application
import com.dj.booksearch.cmp.di.initKoin
import org.koin.android.ext.koin.androidContext

class BookApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        initKoin {
            androidContext(this@BookApplication)
        }
    }
}