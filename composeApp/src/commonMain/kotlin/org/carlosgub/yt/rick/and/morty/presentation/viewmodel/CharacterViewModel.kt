package org.carlosgub.yt.rick.and.morty.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import org.carlosgub.yt.rick.and.morty.domain.repository.CharacterRepository

class CharacterViewModel(
    private val characterRepository: CharacterRepository
) : ViewModel() {

    init {
        getCharacters()
    }

    private fun getCharacters() {
        viewModelScope.launch {
            val characters = characterRepository.getCharacters(1)
            println(characters)
        }
    }
}