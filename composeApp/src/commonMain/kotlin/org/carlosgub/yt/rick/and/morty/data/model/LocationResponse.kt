package org.carlosgub.yt.rick.and.morty.data.model

import kotlinx.serialization.Serializable

@Serializable
data class LocationResponse(
    val info: InfoResponse,
    val results: List<LocationData>,
) {
    @Serializable
    data class LocationData(
        val id: Int,
        val name: String,
        val type: String,
        val dimension: String,
    )
}
