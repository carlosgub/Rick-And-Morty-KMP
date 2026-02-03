package org.carlosgub.yt.rick.and.morty.presentation.viewmodel.characterdetail

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.toRoute
import org.carlosgub.yt.rick.and.morty.domain.repository.CharacterRepository
import org.carlosgub.yt.rick.and.morty.presentation.navigation.Screen
import org.orbitmvi.orbit.ContainerHost
import org.orbitmvi.orbit.container

class CharacterDetailViewModel(
    private val characterRepository: CharacterRepository,
    savedStateHandle: SavedStateHandle,
) : ViewModel(), ContainerHost<CharacterDetailState, CharacterDetailSideEffect> {

    private val characterId: Int = checkNotNull(savedStateHandle["id"]) {
        "Character id is required"
    }

    override val container =
        viewModelScope.container<CharacterDetailState, CharacterDetailSideEffect>(
            initialState = CharacterDetailState(),
            onCreate = { getCharacter() }
        )

    private fun getCharacter() = intent {
        reduce { state.copy(isLoading = true) }
        characterRepository.getCharacter(characterId)
            .onSuccess { character ->
                reduce {
                    state.copy(
                        isLoading = false,
                        character = character,
                        errorMessage = null
                    )
                }
            }
            .onFailure { error ->
                reduce {
                    state.copy(
                        isLoading = false,
                        character = null,
                        errorMessage = error.message ?: "Hubo un error"
                    )
                }
            }

    }

    fun navigateBack() = intent {
        postSideEffect(CharacterDetailSideEffect.NavigateBack)
    }
}