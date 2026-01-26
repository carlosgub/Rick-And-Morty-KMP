package org.carlosgub.yt.rick.and.morty.presentation.viewmodel.characterdetail

sealed class CharacterDetailSideEffect {
    data object NavigateBack: CharacterDetailSideEffect()
}