package org.carlosgub.yt.rick.and.morty.presentation.viewmodel.character

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import org.carlosgub.yt.rick.and.morty.domain.repository.CharacterRepository
import org.orbitmvi.orbit.ContainerHost
import org.orbitmvi.orbit.container

class CharacterViewModel(
    private val characterRepository: CharacterRepository
) : ViewModel(), ContainerHost<CharacterState, CharacterSideEffect> {

    override val container =
        viewModelScope.container<CharacterState, CharacterSideEffect>(CharacterState())

    init {
        getCharacters()
    }

    private fun getCharacters() = intent {
        if (!state.canLoadMore || state.isLoading || state.isPaginating) return@intent

        if (state.characters.isEmpty()) {
            reduce { state.copy(isLoading = true, error = null) }
        } else {
            reduce { state.copy(isPaginating = true, error = null) }
        }

        characterRepository.getCharacters(state.page)
            .onSuccess { characterPaging ->
                reduce {
                    state.copy(
                        isLoading = false,
                        isPaginating = false,
                        characters = state.characters + characterPaging.results,
                        page = state.page + 1,
                        canLoadMore = characterPaging.canLoadMore
                    )
                }
            }
            .onFailure { error ->
                if (state.characters.isNotEmpty()) {
                    reduce {
                        state.copy(
                            isLoading = false,
                            isPaginating = false
                        )
                    }
                    postSideEffect(CharacterSideEffect.ShowSnackbar(error.message ?: "Ocurrió un error inesperado"))
                } else {
                    reduce {
                        state.copy(
                            isLoading = false,
                            isPaginating = false,
                            error = error.message ?: "Ocurrió un error inesperado"
                        )
                    }
                }
            }
    }

    fun loadNextPage() {
        getCharacters()
    }

    fun onCharacterClicked(id: Int) = intent {
        postSideEffect(CharacterSideEffect.NavigateToCharacterDetail(id))
    }
}