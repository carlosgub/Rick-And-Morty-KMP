package org.carlosgub.yt.rick.and.morty.data.repository

import kotlinx.coroutines.test.runTest
import org.carlosgub.yt.rick.and.morty.data.remote.fake.RickAndMortyApiFake
import org.carlosgub.yt.rick.and.morty.data.testdata.TestData.episodeResponse
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue

class EpisodeRepositoryImplTest {
    private val api = RickAndMortyApiFake()
    private val repository = EpisodeRepositoryImpl(api)

    @Test
    fun `getLocations return success result`() =
        runTest {
            api.episodesResult = Result.success(episodeResponse)

            val result = repository.getEpisodes(1)

            assertTrue(result.isSuccess)
            result.onSuccess { locations ->
                assertEquals(1, locations.size)
                assertEquals("Pilot", locations.first().name)
                assertEquals("S0102", locations.first().episode)
            }
        }

    @Test
    fun `getLocations return failure when api fails`() =
        runTest {
            val exception = Exception("Network error")
            api.episodesResult = Result.failure(exception)

            val result = repository.getEpisodes(1)

            assertTrue(result.isFailure)
            result.onFailure { error ->
                assertEquals(exception.message, error.message.orEmpty())
            }
        }
}
