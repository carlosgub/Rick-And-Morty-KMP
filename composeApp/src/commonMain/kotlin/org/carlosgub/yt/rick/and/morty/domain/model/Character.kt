package org.carlosgub.yt.rick.and.morty.domain.model

data class Character(
    val id: Int,
    val name: String,
    val status: String,
    val species: String,
    val gender: String,
    val image: String,
    val origin: String,
    val location: String
)
