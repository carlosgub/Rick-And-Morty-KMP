package org.carlosgub.yt.rick.and.morty.data.mapper

import org.carlosgub.yt.rick.and.morty.data.model.EpisodeResponse
import org.carlosgub.yt.rick.and.morty.domain.model.Episode

fun EpisodeResponse.EpisodeData.toEpisode(): Episode =
    Episode(
        id = id,
        name = name,
        airDate = airDate,
        episode = episode,
    )
