package org.carlosgub.yt.rick.and.morty.presentation.viewmodel.characterdetail

import org.carlosgub.yt.rick.and.morty.domain.model.Character

data class CharacterDetailState(
    val isLoading: Boolean = false,
    val character: Character? = null,
    val errorMessage: String? = null,
)
