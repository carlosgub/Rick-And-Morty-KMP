package org.carlosgub.yt.rick.and.morty.presentation.viewmodel.characterdetail

import androidx.lifecycle.SavedStateHandle
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
import org.carlosgub.yt.rick.and.morty.presentation.viewmodel.character.CharacterSideEffect
import org.carlosgub.yt.rick.and.morty.presentation.viewmodel.character.CharacterState
import org.carlosgub.yt.rick.and.morty.presentation.viewmodel.character.CharacterViewModel
import org.orbitmvi.orbit.test.TestSettings
import org.orbitmvi.orbit.test.test
import kotlin.test.Test

@OptIn(ExperimentalCoroutinesApi::class)
class CharacterDetailViewModelTest {

    val repository = FakeCharacterRepository()
    val savedStateHandle = SavedStateHandle(mapOf("id" to 1))

    @Test
    fun `initial load success`() = runTest {
        val repository = FakeCharacterRepository()
        val character = TestData.characterData.toCharacter()

        repository.characterResult = Result.success(character)

        val viewModel = CharacterDetailViewModel(repository, savedStateHandle)

        viewModel.test(this, CharacterDetailState()) {
            runOnCreate()

            expectState { copy(isLoading = true) }

            expectState {
                copy(
                    isLoading = false,
                    character = character,
                )
            }
        }
    }

    @Test
    fun `initial load failure`() = runTest {
        val errorMessage = "Network error"

        repository.characterResult = Result.failure(Exception(errorMessage))

        val viewModel = CharacterDetailViewModel(repository, savedStateHandle)

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
}
