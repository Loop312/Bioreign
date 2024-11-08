package org.example.bioreign

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform