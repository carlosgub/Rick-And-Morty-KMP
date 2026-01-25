package org.carlosgub.yt.rick.and.morty.presentation.viewmodel.character.detail

import org.carlosgub.yt.rick.and.morty.domain.model.Character

data class CharacterDetailState(
    val character: Character? = null,
    val isLoading: Boolean = false
)
