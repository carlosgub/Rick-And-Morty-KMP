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
            reduce { state.copy(isLoading = true) }
        } else {
            reduce { state.copy(isPaginating = true) }
        }

        try {
            val characterPaging = characterRepository.getCharacters(state.page)
            reduce {
                state.copy(
                    isLoading = false,
                    isPaginating = false,
                    characters = state.characters + characterPaging.results,
                    page = state.page + 1,
                    canLoadMore = characterPaging.canLoadMore
                )
            }
        } catch (e: Exception) {
            e.printStackTrace()
            reduce {
                state.copy(
                    isLoading = false,
                    isPaginating = false
                )
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