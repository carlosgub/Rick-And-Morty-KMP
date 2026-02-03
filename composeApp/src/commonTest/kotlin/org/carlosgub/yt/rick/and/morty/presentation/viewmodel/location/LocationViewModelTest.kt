package org.carlosgub.yt.rick.and.morty.presentation.viewmodel.location

import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.carlosgub.yt.rick.and.morty.data.mapper.toLocation
import org.carlosgub.yt.rick.and.morty.data.repository.fake.FakeLocationRepository
import org.carlosgub.yt.rick.and.morty.data.testdata.TestData
import org.orbitmvi.orbit.test.test
import kotlin.test.Test

@OptIn(ExperimentalCoroutinesApi::class)
class LocationViewModelTest {
    val repository = FakeLocationRepository()
    val viewModel = LocationViewModel(repository)

    @Test
    fun `initial load success`() = runTest {
        val locations = listOf(TestData.locationData.toLocation())

        repository.locationsResult = Result.success(locations)

        viewModel.test(this) {
            runOnCreate()
            expectState { copy(isLoading = true) }
            expectState {
                copy(
                    isLoading = false,
                    locations = locations
                )
            }
        }
    }

    @Test
    fun `initial load failure`() = runTest {
        val errorMessage = "Network error"
        val repository = FakeLocationRepository()
        repository.locationsResult = Result.failure(Exception(errorMessage))

        val viewModel = LocationViewModel(repository)

        viewModel.test(this) {
            runOnCreate()
            expectState { copy(isLoading = true) }
            expectState {
                copy(
                    isLoading = false,
                    locations = emptyList(),
                    errorMessage = errorMessage
                )
            }
        }
    }
}
