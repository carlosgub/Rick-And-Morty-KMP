package org.carlosgub.yt.rick.and.morty.presentation.viewmodel.character

import org.carlosgub.yt.rick.and.morty.domain.model.Character

data class CharacterState(
    val isLoading: Boolean = false,
    val isLoadingNextPage: Boolean = false,
    val characters: List<Character> = emptyList(),
    val canLoadMore: Boolean = true,
    val page: Int = 1,
    val errorMessage: String? = null,
)
