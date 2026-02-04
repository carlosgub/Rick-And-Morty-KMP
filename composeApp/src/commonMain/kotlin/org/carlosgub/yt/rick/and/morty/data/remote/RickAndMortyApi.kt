package org.carlosgub.yt.rick.and.morty.data.remote

import org.carlosgub.yt.rick.and.morty.data.model.CharacterResponse
import org.carlosgub.yt.rick.and.morty.data.model.EpisodeResponse
import org.carlosgub.yt.rick.and.morty.data.model.LocationResponse

interface RickAndMortyApi {
    suspend fun getCharacters(page: Int): Result<CharacterResponse>
    suspend fun getCharacter(id: Int): Result<CharacterResponse.CharacterData>
    suspend fun getLocations(page: Int): Result<LocationResponse>
    suspend fun getEpisodes(page: Int): Result<EpisodeResponse>
}