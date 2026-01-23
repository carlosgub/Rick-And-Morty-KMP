package org.carlosgub.yt.rick.and.morty.data.remote

import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.request.parameter
import org.carlosgub.yt.rick.and.morty.data.model.CharacterResponse
import org.carlosgub.yt.rick.and.morty.data.model.EpisodeResponse
import org.carlosgub.yt.rick.and.morty.data.model.LocationResponse

class RickAndMortyApi(
    private val httpClient: HttpClient
) {
    suspend fun getCharacters(page: Int): CharacterResponse {
        return httpClient.get("/api/character") {
            parameter("page", page)
        }.body()
    }

    suspend fun getLocations(page: Int): LocationResponse {
        return httpClient.get("/api/location") {
            parameter("page", page)
        }.body()
    }

    suspend fun getEpisodes(page: Int): EpisodeResponse {
        return httpClient.get("/api/episode") {
            parameter("page", page)
        }.body()
    }
}