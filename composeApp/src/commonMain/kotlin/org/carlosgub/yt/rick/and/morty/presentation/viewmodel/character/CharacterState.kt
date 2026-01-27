package org.carlosgub.yt.rick.and.morty.presentation.viewmodel.character

import org.carlosgub.yt.rick.and.morty.domain.model.Character

data class CharacterState(
    val isLoading: Boolean = false,
    val characters: List<Character> = emptyList(),
    val page: Int = 1,
    val canLoadMore: Boolean = true,
    val isPaginating: Boolean = false,
    val error: String? = null,
)