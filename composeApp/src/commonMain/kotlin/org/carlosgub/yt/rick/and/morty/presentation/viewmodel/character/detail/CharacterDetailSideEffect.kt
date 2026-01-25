package org.carlosgub.yt.rick.and.morty.presentation.viewmodel.character.detail

sealed class CharacterDetailSideEffect {
    object OnBackPress : CharacterDetailSideEffect()
}
