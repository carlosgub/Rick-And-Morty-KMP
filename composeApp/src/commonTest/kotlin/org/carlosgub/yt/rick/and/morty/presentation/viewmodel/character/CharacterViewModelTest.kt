package org.carlosgub.yt.rick.and.morty.presentation.viewmodel.character

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.carlosgub.yt.rick.and.morty.data.mapper.toCharacter
import org.carlosgub.yt.rick.and.morty.data.repository.fake.FakeCharacterRepository
import org.carlosgub.yt.rick.and.morty.data.testdata.TestData
import org.carlosgub.yt.rick.and.morty.domain.model.CharacterPaging
import org.orbitmvi.orbit.test.TestSettings
import org.orbitmvi.orbit.test.test
import kotlin.test.Test

@OptIn(ExperimentalCoroutinesApi::class)
class CharacterViewModelTest {

    val repository = FakeCharacterRepository()
    val viewModel = CharacterViewModel(repository)

    @Test
    fun `initial load success`() = runTest {
        val characters = listOf(TestData.characterData.toCharacter())
        val paging = CharacterPaging(
            characters = characters,
            canLoadMore = true
        )

        repository.charactersResult = Result.success(paging)



        viewModel.test(
            this, CharacterState(),
        ) {
            runOnCreate()
            expectState { copy(isLoading = true) }
            expectState {
                copy(
                    isLoading = false,
                    characters = characters,
                    canLoadMore = true,
                    page = 2
                )
            }
        }
    }

    @Test
    fun `initial load failure`() = runTest {
        val errorMessage = "Network error"

        repository.charactersResult = Result.failure(Exception(errorMessage))

        viewModel.test(this) {
            runOnCreate()
            expectState { copy(isLoading = true) }
            expectState {
                copy(
                    isLoading = false,
                    errorMessage = errorMessage
                )
            }
        }
    }

    @Test
    fun `initial load failure with default error`() = runTest {
        repository.charactersResult = Result.failure(Exception())

        viewModel.test(this) {
            runOnCreate()
            expectState { copy(isLoading = true) }
            expectState {
                copy(
                    isLoading = false,
                    errorMessage = "Hubo un error"
                )
            }
        }
    }

    @Test
    fun `load next page success`() = runTest {
        val characters = listOf(TestData.characterData.toCharacter())
        val paging = CharacterPaging(
            characters = characters,
            canLoadMore = true
        )

        repository.charactersResult = Result.success(paging)

        viewModel.test(this) {
            runOnCreate()
            // Skip initial load states
            expectState { copy(isLoading = true) }
            expectState {
                copy(
                    isLoading = false,
                    characters = characters,
                    canLoadMore = true,
                    page = 2
                )
            }

            containerHost.loadNextPage()

            expectState { copy(isLoadingNextPage = true) }
            expectState {
                copy(
                    isLoadingNextPage = false,
                    characters = characters + characters,
                    canLoadMore = true,
                    page = 3
                )
            }
        }
    }

    @Test
    fun `onCharacterClicked emits navigation side effect`() = runTest {
        Dispatchers.setMain(StandardTestDispatcher(testScheduler))
        // Mock success to avoid errors during init
        val characters = listOf(TestData.characterData.toCharacter())
        val paging = CharacterPaging(
            characters = characters,
            canLoadMore = true
        )
        val repository = FakeCharacterRepository()
        repository.charactersResult = Result.success(paging)

        val viewModel = CharacterViewModel(repository)

        viewModel.test(this) {
            containerHost.onCharacterClicked(1)
            expectSideEffect(CharacterSideEffect.NavigateToCharacterDetail(1))
        }
    }

    @Test
    fun `load next page failure emits snackbar`() = runTest {
        // Init with success
        val characters = listOf(TestData.characterData.toCharacter())
        val paging = CharacterPaging(
            characters = characters,
            canLoadMore = true
        )
        repository.charactersResult = Result.success(paging)

        viewModel.test(this) {
            runOnCreate()
            expectState { copy(isLoading = true) }
            expectState {
                copy(
                    isLoading = false,
                    characters = characters,
                    canLoadMore = true,
                    page = 2
                )
            }

            // Simulate next page failure
            val errorMessage = "Network error"
            repository.charactersResult = Result.failure(Exception(errorMessage))

            containerHost.loadNextPage()

            expectState { copy(isLoadingNextPage = true) }
            expectState { copy(isLoadingNextPage = false) }
            expectSideEffect(CharacterSideEffect.ShowSnackBar(errorMessage))
        }
    }



    @Test
    fun `load next page failure emits snackbar default error`() = runTest {
        // Init with success
        val characters = listOf(TestData.characterData.toCharacter())
        val paging = CharacterPaging(
            characters = characters,
            canLoadMore = true
        )
        repository.charactersResult = Result.success(paging)

        viewModel.test(this) {
            runOnCreate()
            expectState { copy(isLoading = true) }
            expectState {
                copy(
                    isLoading = false,
                    characters = characters,
                    canLoadMore = true,
                    page = 2
                )
            }

            // Simulate next page failure
            repository.charactersResult = Result.failure(Exception())

            containerHost.loadNextPage()

            expectState { copy(isLoadingNextPage = true) }
            expectState { copy(isLoadingNextPage = false) }
            expectSideEffect(CharacterSideEffect.ShowSnackBar("Hubo un error"))
        }
    }
}
