package org.carlosgub.yt.rick.and.morty.data.mapper

import org.carlosgub.yt.rick.and.morty.data.model.EpisodeResponse
import org.carlosgub.yt.rick.and.morty.data.model.LocationResponse
import org.carlosgub.yt.rick.and.morty.domain.model.Episode
import org.carlosgub.yt.rick.and.morty.domain.model.Location

fun EpisodeResponse.EpisodeData.toEpisode(): Episode {
    return Episode(
        id = id,
        name = name,
        airDate = airDate,
        episode = episode,
    )
}