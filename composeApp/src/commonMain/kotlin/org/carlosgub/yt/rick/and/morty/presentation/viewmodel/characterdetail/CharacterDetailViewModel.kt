package org.carlosgub.yt.rick.and.morty.presentation.viewmodel.characterdetail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import org.carlosgub.yt.rick.and.morty.domain.repository.CharacterRepository
import org.orbitmvi.orbit.ContainerHost
import org.orbitmvi.orbit.container

class CharacterDetailViewModel(
    private val characterRepository: CharacterRepository,
    private val characterId: Int,
) : ViewModel(),
    ContainerHost<CharacterDetailState, CharacterDetailSideEffect> {
    override val container =
        viewModelScope.container<CharacterDetailState, CharacterDetailSideEffect>(
            CharacterDetailState(),
            onCreate = { getCharacter() },
        )

    private fun getCharacter() =
        intent {
            reduce { state.copy(isLoading = true) }
            characterRepository
                .getCharacter(characterId)
                .onSuccess { character ->
                    reduce {
                        state.copy(
                            isLoading = false,
                            character = character,
                            errorMessage = null,
                        )
                    }
                }.onFailure { error ->
                    reduce {
                        state.copy(
                            isLoading = false,
                            character = null,
                            errorMessage = error.message ?: "Hubo un error",
                        )
                    }
                }
        }

    fun navigateBack() =
        intent {
            postSideEffect(CharacterDetailSideEffect.NavigateBack)
        }
}
