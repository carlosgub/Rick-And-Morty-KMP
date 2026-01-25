package org.carlosgub.yt.rick.and.morty.presentation.viewmodel.character

sealed class CharacterSideEffect {
    data class NavigateToDetail(val id: Int) : CharacterSideEffect()
}