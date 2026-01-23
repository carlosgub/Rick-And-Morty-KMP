package org.carlosgub.yt.rick.and.morty.presentation.screen

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
            try {
                val characters = characterRepository.getCharacters(1)
                println("Characters Response: $characters")
            } catch (e: Exception) {
                println("Error fetching characters: ${e.message}")
            }
        }
    }
}
