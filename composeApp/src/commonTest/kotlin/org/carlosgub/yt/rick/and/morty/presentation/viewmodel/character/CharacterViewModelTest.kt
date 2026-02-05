package org.carlosgub.yt.rick.and.morty.presentation.viewmodel.character

import kotlinx.coroutines.test.runTest
import org.carlosgub.yt.rick.and.morty.data.mapper.toCharacter
import org.carlosgub.yt.rick.and.morty.data.repository.fake.CharacterRepositoryFake
import org.carlosgub.yt.rick.and.morty.data.testdata.TestData
import org.carlosgub.yt.rick.and.morty.domain.model.CharacterPaging
import org.orbitmvi.orbit.test.test
import kotlin.test.Test

class CharacterViewModelTest {

    val repository = CharacterRepositoryFake()
    val viewModel = CharacterViewModel(repository)

    @Test
    fun `initial load success`() = runTest {
        val characters = listOf(TestData.characterData.toCharacter())
        val paging = CharacterPaging(characters, true)
        repository.charactersResult = Result.success(paging)

        viewModel.test(
            this, CharacterState()
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
                    characters = characters,
                    canLoadMore = true,
                    page = 2,
                    errorMessage = null
                )
            }
        }
    }

    @Test
    fun `load next page is success`() = runTest {
        val characters = listOf(TestData.characterData.toCharacter())
        val paging = CharacterPaging(characters, true)
        repository.charactersResult = Result.success(paging)

        viewModel.test(
            this, CharacterState()
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
                    characters = characters,
                    canLoadMore = true,
                    page = 2,
                    errorMessage = null
                )
            }
            viewModel.loadNextPage()
            expectState {
                copy(
                    isLoadingNextPage = true,
                )
            }
            expectState {
                copy(
                    isLoadingNextPage = false,
                    characters = characters + characters,
                    canLoadMore = true,
                    page = 3,
                    errorMessage = null
                )
            }
        }
    }

    @Test
    fun `initial load fails`() = runTest {
        val errorMessage = "Network error"
        repository.charactersResult = Result.failure(Exception(errorMessage))

        viewModel.test(
            this, CharacterState()
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

    @Test
    fun `onCharacterItemClicked emits navigation side effect`() = runTest {
        viewModel.test(
            this, CharacterState()
        ) {
            containerHost.onCharacterClicked(1)
            expectSideEffect(CharacterSideEffect.NavigateToCharacterDetail(1))
        }
    }
}