package org.carlosgub.yt.rick.and.morty.data.remote.fake

import org.carlosgub.yt.rick.and.morty.data.model.CharacterResponse
import org.carlosgub.yt.rick.and.morty.data.model.EpisodeResponse
import org.carlosgub.yt.rick.and.morty.data.model.LocationResponse
import org.carlosgub.yt.rick.and.morty.data.remote.RickAndMortyApi

class RickAndMortyApiFake : RickAndMortyApi {

    var charactersResult: Result<CharacterResponse>? = null
    var characterResult: Result<CharacterResponse.CharacterData>? = null
    var locationsResult: Result<LocationResponse>? = null
    var episodesResult: Result<EpisodeResponse>? = null

    override suspend fun getCharacters(page: Int): Result<CharacterResponse> {
        return charactersResult?: Result.failure(Exception("No se inicializo este fake"))
    }

    override suspend fun getCharacter(id: Int): Result<CharacterResponse.CharacterData> {
        return characterResult?: Result.failure(Exception("No se inicializo este fake"))
    }

    override suspend fun getLocations(page: Int): Result<LocationResponse> {
        return locationsResult?: Result.failure(Exception("No se inicializo este fake"))
    }

    override suspend fun getEpisodes(page: Int): Result<EpisodeResponse> {
        return episodesResult?: Result.failure(Exception("No se inicializo este fake"))
    }
}