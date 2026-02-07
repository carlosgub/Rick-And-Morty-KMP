package org.carlosgub.yt.rick.and.morty.data.repository.fake

import org.carlosgub.yt.rick.and.morty.domain.model.Episode
import org.carlosgub.yt.rick.and.morty.domain.repository.EpisodeRepository

class EpisodeRepositoryFake : EpisodeRepository {
    var episodeResult: Result<List<Episode>>? = null

    override suspend fun getEpisodes(page: Int): Result<List<Episode>> =
        episodeResult ?: Result.failure(Exception("No se inicializo este fake"))
}
