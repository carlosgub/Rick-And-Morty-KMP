package org.carlosgub.yt.rick.and.morty.presentation.viewmodel.character

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import org.carlosgub.yt.rick.and.morty.domain.repository.CharacterRepository
import org.orbitmvi.orbit.ContainerHost
import org.orbitmvi.orbit.container

class CharacterViewModel(
    private val characterRepository: CharacterRepository,
) : ViewModel(),
    ContainerHost<CharacterState, CharacterSideEffect> {
    override val container =
        viewModelScope.container<CharacterState, CharacterSideEffect>(
            CharacterState(),
            onCreate = {
                getCharacters()
            },
        )

    private fun getCharacters() =
        intent {
            println(state.toString())
            if (state.isLoading || !state.canLoadMore || state.isLoadingNextPage) return@intent

            if (state.characters.isEmpty()) {
                reduce { state.copy(isLoading = true) }
            } else {
                reduce { state.copy(isLoadingNextPage = true) }
            }

            characterRepository
                .getCharacters(state.page)
                .onSuccess { result ->
                    reduce {
                        state.copy(
                            isLoading = false,
                            isLoadingNextPage = false,
                            characters = state.characters + result.characters,
                            canLoadMore = result.canLoadMore,
                            page = state.page + 1,
                            errorMessage = null,
                        )
                    }
                }.onFailure { error ->
                    if (state.characters.isEmpty()) {
                        reduce {
                            state.copy(
                                isLoading = false,
                                isLoadingNextPage = false,
                                errorMessage = error.message ?: "Hubo un error",
                            )
                        }
                    } else {
                        reduce {
                            state.copy(
                                isLoading = false,
                                isLoadingNextPage = false,
                            )
                        }
                        postSideEffect(
                            CharacterSideEffect.ShowSnackBar(
                                error.message ?: "Hubo un error",
                            ),
                        )
                    }
                }
        }

    fun loadNextPage() =
        intent {
            getCharacters()
        }

    fun onCharacterClicked(id: Int) =
        intent {
            postSideEffect(CharacterSideEffect.NavigateToCharacterDetail(id))
        }
}
