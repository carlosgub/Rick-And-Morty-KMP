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

    private val characterId: Int = savedStateHandle.toRoute<Screen.CharacterDetail>().id

    override val container =
        viewModelScope.container<CharacterDetailState, CharacterDetailSideEffect>(
            CharacterDetailState()
        )

    init {
        getCharacter()
    }

    private fun getCharacter() = intent {
        reduce { state.copy(isLoading = true) }
        characterRepository.getCharacter(characterId)
            .onSuccess { character ->
                reduce {
                    state.copy(
                        isLoading = false,
                        character = character
                    )
                }
            }
            .onFailure { error ->
                println(error.toString())
                reduce {
                    state.copy(
                        isLoading = false,
                        error = error.message ?: "Ocurrió un error inesperado"
                    )
                }
            }
    }

    fun navigateBack() = intent {
        postSideEffect(CharacterDetailSideEffect.NavigateBack)
    }
}