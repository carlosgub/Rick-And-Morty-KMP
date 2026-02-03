package org.carlosgub.yt.rick.and.morty.data.repository.fake

import org.carlosgub.yt.rick.and.morty.domain.model.Episode
import org.carlosgub.yt.rick.and.morty.domain.repository.EpisodeRepository

class FakeEpisodeRepository : EpisodeRepository {

    var episodesResult: Result<List<Episode>>? = null

    override suspend fun getEpisodes(page: Int): Result<List<Episode>> {
        return episodesResult ?: Result.failure(Exception("Not initialized"))
    }
}