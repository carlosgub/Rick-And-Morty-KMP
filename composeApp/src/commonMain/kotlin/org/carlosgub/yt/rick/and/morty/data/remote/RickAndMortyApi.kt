package org.carlosgub.yt.rick.and.morty.data.remote

import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.request.parameter
import org.carlosgub.yt.rick.and.morty.data.model.CharacterResponse
import org.carlosgub.yt.rick.and.morty.data.model.EpisodeResponse
import org.carlosgub.yt.rick.and.morty.data.model.LocationResponse

import io.ktor.client.statement.HttpResponse

class RickAndMortyApi(
    private val httpClient: HttpClient
) {
    suspend fun getCharacters(page: Int): Result<CharacterResponse> {
        return runCatching {
            safeApiCall {
                httpClient.get("/api/character") {
                    parameter("page", page)
                }
            }
        }
    }

    suspend fun getCharacter(id: Int): CharacterResponse.CharacterData {
        return safeApiCall {
            httpClient.get("/api/character/$id")
        }
    }

    suspend fun getLocations(page: Int): LocationResponse {
        return safeApiCall {
            httpClient.get("/api/location") {
                parameter("page", page)
            }
        }
    }

    suspend fun getEpisodes(page: Int): EpisodeResponse {
        return safeApiCall {
            httpClient.get("/api/episode") {
                parameter("page", page)
            }
        }
    }

    private suspend inline fun <reified T> safeApiCall(block: () -> HttpResponse): T {
        val response = block()
        if (response.status.value == 429) {
            throw Exception("Se realizaron muchas peticiones, intente más tarde nuevamente")
        }
        return response.body()
    }
}