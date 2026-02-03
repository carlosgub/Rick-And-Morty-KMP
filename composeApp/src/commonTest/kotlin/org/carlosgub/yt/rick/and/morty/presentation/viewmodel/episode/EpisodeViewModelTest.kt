package org.carlosgub.yt.rick.and.morty.presentation.viewmodel.episode

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.carlosgub.yt.rick.and.morty.data.mapper.toEpisode
import org.carlosgub.yt.rick.and.morty.data.repository.fake.FakeEpisodeRepository
import org.carlosgub.yt.rick.and.morty.data.testdata.TestData
import org.orbitmvi.orbit.test.test
import kotlin.test.Test

@OptIn(ExperimentalCoroutinesApi::class)
class EpisodeViewModelTest {

    val repository = FakeEpisodeRepository()
    val viewModel = EpisodeViewModel(repository)

    @Test
    fun `initial load success`() = runTest {
        val episodes = listOf(TestData.episodeData.toEpisode())
        repository.episodesResult = Result.success(episodes)

        viewModel.test(this) {
            runOnCreate()
            expectState { copy(isLoading = true) }
            expectState {
                copy(
                    isLoading = false,
                    episodes = episodes
                )
            }
        }
    }

    @Test
    fun `initial load failure`() = runTest {
        val errorMessage = "Network error"
        repository.episodesResult = Result.failure(Exception(errorMessage))

        viewModel.test(this) {
            runOnCreate()
            expectState { copy(isLoading = true) }
            expectState {
                copy(
                    isLoading = false,
                    episodes = emptyList(),
                    errorMessage = errorMessage
                )
            }
        }
    }
}
