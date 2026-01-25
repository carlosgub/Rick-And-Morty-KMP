package org.carlosgub.yt.rick.and.morty.data.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class EpisodeResponse(
    val info: InfoResponse,
    val results: List<EpisodeData>
) {
    @Serializable
    data class EpisodeData(
        val id: Int,
        val name: String,
        @SerialName("air_date")
        val airDate: String,
        val episode: String,
    )
}
