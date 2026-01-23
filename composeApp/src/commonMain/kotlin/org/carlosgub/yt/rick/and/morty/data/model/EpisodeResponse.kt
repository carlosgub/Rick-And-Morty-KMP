package org.carlosgub.yt.rick.and.morty.data.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class EpisodeResponse(
    val info: Info,
    val results: List<EpisodeDTO>
) {
    @Serializable
    data class Info(
        val count: Int,
        val pages: Int,
        val next: String?,
        val prev: String?
    )

    @Serializable
    data class EpisodeDTO(
        val id: Int,
        val name: String,
        @SerialName("air_date")
        val airDate: String,
        val episode: String,
        val characters: List<String>,
        val url: String,
        val created: String
    )
}
