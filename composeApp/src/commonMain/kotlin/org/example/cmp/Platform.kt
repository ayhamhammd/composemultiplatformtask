package org.example.cmp

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform