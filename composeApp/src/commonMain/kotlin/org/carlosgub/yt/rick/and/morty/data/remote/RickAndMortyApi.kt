package org.carlosgub.yt.rick.and.morty.data.remote

import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.request.parameter
import io.ktor.client.statement.HttpResponse
import io.ktor.http.HttpStatusCode
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.withContext
import org.carlosgub.yt.rick.and.morty.data.model.CharacterResponse
import org.carlosgub.yt.rick.and.morty.data.model.EpisodeResponse
import org.carlosgub.yt.rick.and.morty.data.model.LocationResponse

class RickAndMortyApiImpl(
    private val httpClient: HttpClient
) : RickAndMortyApi {
    override suspend fun getCharacters(page: Int): Result<CharacterResponse> {
        return safeApiCall {
            httpClient.get("/api/character") {
                parameter("page", page)
            }
        }
    }

    override suspend fun getCharacter(id: Int): Result<CharacterResponse.CharacterData> {
        return safeApiCall {
            httpClient.get("/api/character/$id").body()
        }
    }

    override suspend fun getLocations(page: Int): Result<LocationResponse> {
        return safeApiCall {
            httpClient.get("/api/location") {
                parameter("page", page)
            }.body()
        }
    }

    override suspend fun getEpisodes(page: Int): Result<EpisodeResponse> {
        return safeApiCall {
            httpClient.get("/api/episode") {
                parameter("page", page)
            }.body()
        }
    }

    private suspend inline fun <reified T> safeApiCall(crossinline body: suspend () -> HttpResponse): Result<T> {
        return runCatching {
            withContext(Dispatchers.IO) {
                val response = body()
                if (response.status == HttpStatusCode.TooManyRequests) {
                    throw Exception(TOO_MANY_REQUESTS_MESSAGE)
                }
                response.body<T>()
            }
        }
    }

    companion object {
        const val TOO_MANY_REQUESTS_MESSAGE =
            "Hubo un error, realizaste muchas llamadas al servidor. Espera un momento y vuelve a intentarlo"

    }
}

interface RickAndMortyApi {
    suspend fun getCharacters(page: Int): Result<CharacterResponse>
    suspend fun getCharacter(id: Int): Result<CharacterResponse.CharacterData>
    suspend fun getLocations(page: Int): Result<LocationResponse>
    suspend fun getEpisodes(page: Int): Result<EpisodeResponse>
}