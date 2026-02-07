package org.carlosgub.yt.rick.and.morty.presentation.viewmodel.episode

import kotlinx.coroutines.test.runTest
import org.carlosgub.yt.rick.and.morty.data.mapper.toEpisode
import org.carlosgub.yt.rick.and.morty.data.repository.fake.EpisodeRepositoryFake
import org.carlosgub.yt.rick.and.morty.data.testdata.TestData
import org.orbitmvi.orbit.test.test
import kotlin.test.Test

class EpisodeViewModelTest {
    val repository = EpisodeRepositoryFake()
    val viewModel = EpisodeViewModel(repository)

    @Test
    fun `initial load success`() =
        runTest {
            val episodes = TestData.episodeResponse.results.map { it.toEpisode() }
            repository.episodeResult = Result.success(episodes)

            viewModel.test(
                this,
                EpisodeState(),
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
                        episodes = episodes,
                        errorMessage = null,
                    )
                }
            }
        }

    @Test
    fun `initial load fails`() =
        runTest {
            val errorMessage = "Network error"
            repository.episodeResult = Result.failure(Exception(errorMessage))

            viewModel.test(
                this,
                EpisodeState(),
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
                        errorMessage = errorMessage,
                    )
                }
            }
        }
}
