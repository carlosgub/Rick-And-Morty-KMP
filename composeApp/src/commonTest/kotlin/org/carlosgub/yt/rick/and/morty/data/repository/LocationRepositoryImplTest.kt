package org.carlosgub.yt.rick.and.morty.data.repository

import kotlinx.coroutines.test.runTest
import org.carlosgub.yt.rick.and.morty.data.remote.fake.RickAndMortyApiFake
import org.carlosgub.yt.rick.and.morty.data.testdata.TestData.locationResponse
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue

class LocationRepositoryImplTest {
    private val api = RickAndMortyApiFake()
    private val repository = LocationRepositoryImpl(api)

    @Test
    fun `getLocations return success result`() =
        runTest {
            api.locationsResult = Result.success(locationResponse)

            val result = repository.getLocations(1)

            assertTrue(result.isSuccess)
            result.onSuccess { locations ->
                assertEquals(1, locations.size)
                assertEquals("Earth", locations.first().name)
                assertEquals("Planet", locations.first().type)
            }
        }

    @Test
    fun `getLocations return failure when api fails`() =
        runTest {
            val exception = Exception("Network error")
            api.locationsResult = Result.failure(exception)

            val result = repository.getLocations(1)

            assertTrue(result.isFailure)
            result.onFailure { error ->
                assertEquals(exception.message, error.message.orEmpty())
            }
        }
}
