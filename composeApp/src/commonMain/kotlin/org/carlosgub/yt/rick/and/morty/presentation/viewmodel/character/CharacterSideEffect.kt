package org.carlosgub.yt.rick.and.morty.presentation.viewmodel.character

sealed class CharacterSideEffect {
    data class NavigateToCharacterDetail(
        val id: Int,
    ) : CharacterSideEffect()

    data class ShowSnackBar(
        val message: String,
    ) : CharacterSideEffect()
}
