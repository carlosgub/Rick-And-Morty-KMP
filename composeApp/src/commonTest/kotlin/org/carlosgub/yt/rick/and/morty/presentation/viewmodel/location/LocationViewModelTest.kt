package org.carlosgub.yt.rick.and.morty.presentation.viewmodel.location

import kotlinx.coroutines.test.runTest
import org.carlosgub.yt.rick.and.morty.data.mapper.toLocation
import org.carlosgub.yt.rick.and.morty.data.repository.fake.LocationRepositoryFake
import org.carlosgub.yt.rick.and.morty.data.testdata.TestData
import org.orbitmvi.orbit.test.test
import kotlin.test.Test

class LocationViewModelTest {

    val repository = LocationRepositoryFake()
    val viewModel = LocationViewModel(repository)

    @Test
    fun `initial load success`() = runTest {
        val locations = TestData.locationResponse.results.map { it.toLocation() }
        repository.locationResult = Result.success(locations)

        viewModel.test(
            this, LocationState()
        ) {
            runOnCreate()
            expectState {
                copy(
                    isLoading = true,
                )
            }
            expectState {
                copy(
                    isLoading = false,
                    locations = locations,
                    errorMessage = null
                )
            }
        }
    }

    @Test
    fun `initial load fails`() = runTest {
        val errorMessage = "Network error"
        repository.locationResult = Result.failure(Exception(errorMessage))

        viewModel.test(
            this, LocationState()
        ) {
            runOnCreate()
            expectState {
                copy(
                    isLoading = true,
                )
            }
            expectState {
                copy(
                    isLoading = false,
                    errorMessage = errorMessage
                )
            }
        }
    }
}