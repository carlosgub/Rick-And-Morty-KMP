package org.carlosgub.yt.rick.and.morty.presentation.screen.characterdetail.preview

import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import org.carlosgub.yt.rick.and.morty.domain.model.Character
import org.carlosgub.yt.rick.and.morty.presentation.viewmodel.character.CharacterState
import org.carlosgub.yt.rick.and.morty.presentation.viewmodel.characterdetail.CharacterDetailState

class CharacterDetailStateParameterProvider : PreviewParameterProvider<CharacterDetailState> {
    override val values: Sequence<CharacterDetailState> = sequenceOf(
        CharacterDetailState(
            isLoading = true,
            character = null
        ),
        CharacterDetailState(
            isLoading = false,
            character = Character(
                id = 1,
                name = "Rick Sanchez",
                status = "Alive",
                species = "Humano",
                type = "",
                gender = "Male",
                location = "Earth",
                image = ""
            )
        )

    )

}