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
        reduce { state.copy(isLoading = true) }
        val characters = characterRepository.getCharacters(1)
        reduce {
            state.copy(
                isLoading = false,
                characters = characters
            )
        }
    }

    fun onCharacterClicked(id: Int) = intent {
        postSideEffect(CharacterSideEffect.NavigateToCharacterDetail(id))
    }
}