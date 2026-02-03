package org.carlosgub.yt.rick.and.morty.data.repository

import kotlinx.coroutines.test.runTest
import org.carlosgub.yt.rick.and.morty.data.remote.FakeRickAndMortyApi
import org.carlosgub.yt.rick.and.morty.data.testdata.TestData
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue

class EpisodeRepositoryTest {

    private val api = FakeRickAndMortyApi()
    private val repository = EpisodeRepositoryImpl(api)

    @Test
    fun `getEpisodes returns success result with mapped data`() = runTest {
        api.episodesResult = Result.success(TestData.episodeResponse)

        val result = repository.getEpisodes(1)

        assertTrue(result.isSuccess)
        result.onSuccess { episodes ->
            assertEquals(1, episodes.size)
            assertEquals("Pilot", episodes.first().name)
        }
    }

    @Test
    fun `getEpisodes returns failure when api fails`() = runTest {
        val exception = Exception("Network error")
        api.episodesResult = Result.failure(exception)

        val result = repository.getEpisodes(1)

        assertTrue(result.isFailure)
        assertEquals(exception, result.exceptionOrNull())
    }
}
