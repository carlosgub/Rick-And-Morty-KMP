package org.carlosgub.yt.rick.and.morty.presentation.viewmodel.characterdetail

import androidx.lifecycle.SavedStateHandle
import kotlinx.coroutines.test.runTest
import org.carlosgub.yt.rick.and.morty.data.mapper.toCharacter
import org.carlosgub.yt.rick.and.morty.data.repository.fake.CharacterRepositoryFake
import org.carlosgub.yt.rick.and.morty.data.testdata.TestData
import org.carlosgub.yt.rick.and.morty.domain.model.CharacterPaging
import org.carlosgub.yt.rick.and.morty.presentation.viewmodel.character.CharacterSideEffect
import org.carlosgub.yt.rick.and.morty.presentation.viewmodel.character.CharacterState
import org.orbitmvi.orbit.test.test
import kotlin.test.Test

class CharacterDetailViewModelTest {

    val repository = CharacterRepositoryFake()
    val savedStateHandle = SavedStateHandle(mapOf("id" to 1))
    val viewModel = CharacterDetailViewModel(repository,savedStateHandle)

    @Test
    fun `initial load success`() = runTest {
        val character = TestData.characterData.toCharacter()
        repository.characterResult = Result.success(character)

        viewModel.test(
            this, CharacterDetailState()
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
                    character = character,
                    errorMessage = null
                )
            }
        }
    }

    @Test
    fun `initial load fails`() = runTest {
        val errorMessage = "Network error"
        repository.characterResult = Result.failure(Exception(errorMessage))

        viewModel.test(
            this, CharacterDetailState()
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
    fun `onNavigationBack emits navigation side effect`() = runTest {
        viewModel.test(
            this, CharacterDetailState()
        ) {
            containerHost.navigateBack()
            expectSideEffect(CharacterDetailSideEffect.NavigateBack)
        }
    }
}