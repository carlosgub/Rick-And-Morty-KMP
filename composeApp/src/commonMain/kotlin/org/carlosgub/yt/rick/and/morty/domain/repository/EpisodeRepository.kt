package org.carlosgub.yt.rick.and.morty.domain.repository

import org.carlosgub.yt.rick.and.morty.domain.model.Episode

interface EpisodeRepository {
    suspend fun getEpisodes(page: Int): Result<List<Episode>>
}
