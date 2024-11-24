package com.dj.booksearch.cmp

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform