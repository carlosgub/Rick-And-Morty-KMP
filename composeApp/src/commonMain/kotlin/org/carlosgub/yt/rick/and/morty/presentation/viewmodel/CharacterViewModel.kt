package org.carlosgub.yt.rick.and.morty.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import org.carlosgub.yt.rick.and.morty.domain.model.Character
import org.carlosgub.yt.rick.and.morty.domain.repository.CharacterRepository
import org.orbitmvi.orbit.ContainerHost
import org.orbitmvi.orbit.container
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.reduce

data class CharacterState(
    val characters: List<Character> = emptyList(),
    val isLoading: Boolean = false,
    val error: String? = null
)

sealed class CharacterSideEffect

class CharacterViewModel(
    private val characterRepository: CharacterRepository
) : ViewModel(), ContainerHost<CharacterState, CharacterSideEffect> {

    override val container =
        viewModelScope.container<CharacterState, CharacterSideEffect>(CharacterState())


    init {
        getCharacters()
    }

    private fun getCharacters() = intent {
        if (state.characters.isNotEmpty()) return@intent
        reduce { state.copy(isLoading = true) }
        val characters = characterRepository.getCharacters(1)
        reduce {
            state.copy(
                isLoading = false,
                characters = characters
            )
        }
    }


}