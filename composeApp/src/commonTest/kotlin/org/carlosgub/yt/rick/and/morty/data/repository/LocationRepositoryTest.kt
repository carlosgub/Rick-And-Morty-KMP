package org.carlosgub.yt.rick.and.morty.data.repository

import kotlinx.coroutines.test.runTest
import org.carlosgub.yt.rick.and.morty.data.remote.FakeRickAndMortyApi
import org.carlosgub.yt.rick.and.morty.data.testdata.TestData
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue

class LocationRepositoryTest {

    private val api = FakeRickAndMortyApi()
    private val repository = LocationRepositoryImpl(api)

    @Test
    fun `getLocations returns success result with mapped data`() = runTest {
        api.locationsResult = Result.success(TestData.locationResponse)

        val result = repository.getLocations(1)

        assertTrue(result.isSuccess)
        result.onSuccess { locations ->
            assertEquals(1, locations.size)
            assertEquals("Earth", locations.first().name)
        }
    }

    @Test
    fun `getLocations returns failure when api fails`() = runTest {
        val exception = Exception("Network error")
        api.locationsResult = Result.failure(exception)

        val result = repository.getLocations(1)

        assertTrue(result.isFailure)
        assertEquals(exception, result.exceptionOrNull())
    }
}
