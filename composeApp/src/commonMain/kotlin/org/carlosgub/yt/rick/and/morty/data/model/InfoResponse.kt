package org.carlosgub.yt.rick.and.morty.data.model

import kotlinx.serialization.Serializable

@Serializable
data class InfoResponse(
    val count: Int,
    val pages: Int,
    val next: String?,
    val prev: String?,
)
