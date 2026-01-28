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
        println(state.toString())
        if (state.isLoading || !state.canLoadMore || state.isLoadingNextPage) return@intent

        if (state.characters.isEmpty()) {
            reduce { state.copy(isLoading = true) }
        } else {
            reduce { state.copy(isLoadingNextPage = true) }
        }


        try {
            val result = characterRepository.getCharacters(state.page)
            reduce {
                state.copy(
                    isLoading = false,
                    isLoadingNextPage = false,
                    characters = state.characters + result.characters,
                    canLoadMore = result.canLoadMore,
                    page = state.page + 1,
                    errorMessage = null
                )
            }
        }catch (e: Exception){
            println(e.toString())
            reduce {
                state.copy(
                    isLoading = false,
                    isLoadingNextPage = false,
                    page = state.page + 1,
                    errorMessage = e.message?:"Hubo un error"
                )
            }
        }
    }

    fun loadNextPage() = intent {
        getCharacters()
    }

    fun onCharacterClicked(id: Int) = intent {
        postSideEffect(CharacterSideEffect.NavigateToCharacterDetail(id))
    }
}