package org.carlosgub.yt.rick.and.morty.data.remote

import io.ktor.client.HttpClient
import io.ktor.client.engine.mock.MockEngine
import io.ktor.client.engine.mock.respond
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.http.HttpHeaders
import io.ktor.http.HttpStatusCode
import io.ktor.http.headersOf
import io.ktor.serialization.kotlinx.json.json
import kotlinx.coroutines.test.runTest
import kotlinx.serialization.json.Json
import org.carlosgub.yt.rick.and.morty.data.remote.impl.RickAndMortyApiImpl
import org.carlosgub.yt.rick.and.morty.data.remote.impl.RickAndMortyApiImpl.Companion.TOO_MANY_REQUEST_MESSAGE
import org.carlosgub.yt.rick.and.morty.data.remote.responses.MockResponses
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue

class RickAndMortyApiTest {
    private fun provideRickAndMortyApi(
        response: String,
        status: HttpStatusCode = HttpStatusCode.OK,
    ): RickAndMortyApiImpl {
        val mockEngine = MockEngine { _ ->
            respond(
                content = response,
                status = status,
                headers = headersOf(HttpHeaders.ContentType, "application/json"),
            )
        }
        val httpClient = HttpClient(mockEngine) {
            install(ContentNegotiation) {
                json(
                    Json {
                        ignoreUnknownKeys = true
                        prettyPrint = true
                    },
                )
            }
        }
        return RickAndMortyApiImpl(httpClient)
    }

    @Test
    fun `getCharacters returns success result`() =
        runTest {
            val api = provideRickAndMortyApi(
                response = MockResponses.GET_CHARACTERS_RESPONSE,
                status = HttpStatusCode.OK,
            )
            val result = api.getCharacters(1)

            assertTrue(result.isSuccess)
            result.onSuccess { result ->
                assertEquals(result.info.count, 826)
                assertEquals(result.results.size, 4)
            }
        }

    @Test
    fun `getCharacter returns success result`() =
        runTest {
            val characterId = 2
            val api = provideRickAndMortyApi(
                response = MockResponses.GET_CHARACTER_RESPONSE,
                status = HttpStatusCode.OK,
            )
            val result = api.getCharacter(characterId)

            assertTrue(result.isSuccess)
            result.onSuccess { result ->
                assertEquals(result.id, characterId)
                assertEquals(result.name, "Morty Smith")
            }
        }

    @Test
    fun `getLocations returns success result`() =
        runTest {
            val api = provideRickAndMortyApi(
                response = MockResponses.GET_LOCATIONS_RESPONSE,
                status = HttpStatusCode.OK,
            )
            val result = api.getLocations(1)

            assertTrue(result.isSuccess)
            result.onSuccess { result ->
                assertEquals(result.info.count, 126)
                assertEquals(result.results.size, 1)
            }
        }

    @Test
    fun `getEpisodes returns success result`() =
        runTest {
            val api = provideRickAndMortyApi(
                response = MockResponses.GET_EPISODES_RESPONSE,
                status = HttpStatusCode.OK,
            )
            val result = api.getEpisodes(1)

            assertTrue(result.isSuccess)
            result.onSuccess { result ->
                assertEquals(result.info.count, 51)
                assertEquals(result.results.size, 1)
            }
        }

    @Test
    fun `api returns error 429`() =
        runTest {
            val api = provideRickAndMortyApi(
                response = "",
                status = HttpStatusCode.TooManyRequests,
            )
            val result = api.getEpisodes(1)

            assertTrue(result.isFailure)
            result.onFailure { error ->
                assertEquals(error.message, TOO_MANY_REQUEST_MESSAGE)
            }
        }

    @Test
    fun `api returns error 500`() =
        runTest {
            val api = provideRickAndMortyApi(
                response = "",
                status = HttpStatusCode.InternalServerError,
            )
            val result = api.getEpisodes(1)

            assertTrue(result.isFailure)
        }
}
