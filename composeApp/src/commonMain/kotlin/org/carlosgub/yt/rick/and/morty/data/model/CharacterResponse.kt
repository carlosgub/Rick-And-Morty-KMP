package org.carlosgub.yt.rick.and.morty.data.model

import kotlinx.serialization.Serializable

@Serializable
data class CharacterResponse(
    val info: InfoResponse,
    val results: List<CharacterData>,
) {
    @Serializable
    data class CharacterData(
        val id: Int,
        val name: String,
        val status: String,
        val species: String,
        val type: String,
        val gender: String,
        val origin: LocationData,
        val location: LocationData,
        val image: String,
        val episode: List<String>,
        val url: String,
        val created: String,
    ) {
        @Serializable
        data class LocationData(
            val name: String,
            val url: String,
        )
    }
}
