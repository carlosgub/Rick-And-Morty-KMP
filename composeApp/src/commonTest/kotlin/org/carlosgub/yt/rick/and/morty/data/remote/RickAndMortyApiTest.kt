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
import org.carlosgub.yt.rick.and.morty.data.remote.RickAndMortyApi.Companion.TOO_MANY_REQUESTS_MESSAGE
import org.carlosgub.yt.rick.and.morty.data.remote.responses.MockResponses
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue

class RickAndMortyApiTest {

    private fun provideRickAndMortyApi(
        responseContent: String,
        status: HttpStatusCode = HttpStatusCode.OK
    ): RickAndMortyApi {
        val mockEngine = MockEngine { _ ->
            respond(
                content = responseContent,
                status = status,
                headers = headersOf(HttpHeaders.ContentType, "application/json")
            )
        }
        val httpClient = HttpClient(mockEngine) {
            install(ContentNegotiation) {
                json(Json { ignoreUnknownKeys = true })
            }
        }
        return RickAndMortyApi(httpClient)
    }

    @Test
    fun `getCharacters returns success result`() = runTest {
        val api = provideRickAndMortyApi(MockResponses.GET_CHARACTERS_RESPONSE)
        val result = api.getCharacters(1)

        assertTrue(result.isSuccess)
        val response = result.getOrThrow()
        assertEquals(1, response.results.size)
        assertEquals("Rick Sanchez", response.results.first().name)
    }

    @Test
    fun `getCharacter returns success result`() = runTest {
        val api = provideRickAndMortyApi(MockResponses.GET_CHARACTER_RESPONSE)
        val result = api.getCharacter(1)

        assertTrue(result.isSuccess)
        val response = result.getOrThrow()
        assertEquals("Rick Sanchez", response.name)
    }

    @Test
    fun `getLocations returns success result`() = runTest {
        val api = provideRickAndMortyApi(MockResponses.GET_LOCATIONS_RESPONSE)
        val result = api.getLocations(1)

        assertTrue(result.isSuccess)
        val response = result.getOrThrow()
        assertEquals(1, response.results.size)
        assertEquals("Earth", response.results.first().name)
    }

    @Test
    fun `getEpisodes returns success result`() = runTest {
        val api = provideRickAndMortyApi(MockResponses.GET_EPISODES_RESPONSE)
        val result = api.getEpisodes(1)

        assertTrue(result.isSuccess)
        val response = result.getOrThrow()
        assertEquals(1, response.results.size)
        assertEquals("Pilot", response.results.first().name)
    }

    @Test
    fun `api returns error on 429`() = runTest {
        val api = provideRickAndMortyApi("", HttpStatusCode.TooManyRequests)
        val result = api.getCharacters(1)

        assertTrue(result.isFailure)
        assertEquals(
            TOO_MANY_REQUESTS_MESSAGE,
            result.exceptionOrNull()?.message
        )
    }

    @Test
    fun `api returns failure on 500 error`() = runTest {
        val api = provideRickAndMortyApi("", HttpStatusCode.InternalServerError)
        val result = api.getCharacters(1)

        assertTrue(result.isFailure)
    }
}
