package org.carlosgub.yt.rick.and.morty.data.repository

import org.carlosgub.yt.rick.and.morty.data.mapper.toEpisode
import org.carlosgub.yt.rick.and.morty.data.remote.RickAndMortyApi
import org.carlosgub.yt.rick.and.morty.domain.model.Episode
import org.carlosgub.yt.rick.and.morty.domain.repository.EpisodeRepository

class EpisodeRepositoryImpl(
    private val api: RickAndMortyApi
) : EpisodeRepository {
    override suspend fun getEpisodes(page: Int): List<Episode> {
        return api.getEpisodes(page).results.map { it.toEpisode() }
    }
}
