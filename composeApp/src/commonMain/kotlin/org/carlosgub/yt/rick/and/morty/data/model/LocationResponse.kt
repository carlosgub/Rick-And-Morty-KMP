package org.carlosgub.yt.rick.and.morty.data.model

import kotlinx.serialization.Serializable

@Serializable
data class LocationResponse(
    val info: Info,
    val results: List<LocationDTO>
) {
    @Serializable
    data class Info(
        val count: Int,
        val pages: Int,
        val next: String?,
        val prev: String?
    )

    @Serializable
    data class LocationDTO(
        val id: Int,
        val name: String,
        val type: String,
        val dimension: String,
        val residents: List<String>,
        val url: String,
        val created: String
    )
}
