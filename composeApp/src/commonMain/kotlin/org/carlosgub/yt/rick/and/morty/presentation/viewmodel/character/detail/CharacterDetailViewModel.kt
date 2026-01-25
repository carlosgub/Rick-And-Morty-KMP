package org.carlosgub.yt.rick.and.morty.presentation.viewmodel.character.detail

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
    savedStateHandle: SavedStateHandle
) : ViewModel(), ContainerHost<CharacterDetailState, CharacterDetailSideEffect> {

    private val characterId = savedStateHandle.toRoute<Screen.CharacterDetail>().id

    override val container = viewModelScope.container<CharacterDetailState, CharacterDetailSideEffect>(CharacterDetailState())

    init {
        getCharacterDetail()
    }

    private fun getCharacterDetail() = intent {
        reduce { state.copy(isLoading = true) }
        val character = characterRepository.getCharacter(characterId)
        reduce {
            state.copy(
                isLoading = false,
                character = character
            )
        }
    }

    fun onBackPress() = intent {
        postSideEffect(CharacterDetailSideEffect.OnBackPress)
    }
}
