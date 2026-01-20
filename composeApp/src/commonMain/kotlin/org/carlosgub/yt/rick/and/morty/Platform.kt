package org.carlosgub.yt.rick.and.morty

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform