package org.carlosgub.yt.rick.and.morty.data.remote

import org.carlosgub.yt.rick.and.morty.data.model.CharacterResponse
import org.carlosgub.yt.rick.and.morty.data.model.EpisodeResponse
import org.carlosgub.yt.rick.and.morty.data.model.LocationResponse

class FakeRickAndMortyApi : RickAndMortyApi {
    
    var charactersResult: Result<CharacterResponse>? = null
    var characterResult: Result<CharacterResponse.CharacterData>? = null
    var locationsResult: Result<LocationResponse>? = null
    var episodesResult: Result<EpisodeResponse>? = null

    override suspend fun getCharacters(page: Int): Result<CharacterResponse> {
        return charactersResult ?: Result.failure(Exception("Not initialized"))
    }

    override suspend fun getCharacter(id: Int): Result<CharacterResponse.CharacterData> {
        return characterResult ?: Result.failure(Exception("Not initialized"))
    }

    override suspend fun getLocations(page: Int): Result<LocationResponse> {
        return locationsResult ?: Result.failure(Exception("Not initialized"))
    }

    override suspend fun getEpisodes(page: Int): Result<EpisodeResponse> {
        return episodesResult ?: Result.failure(Exception("Not initialized"))
    }
}
