package org.carlosgub.yt.rick.and.morty.presentation.screen.character.preview

import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import org.carlosgub.yt.rick.and.morty.domain.model.Character
import org.carlosgub.yt.rick.and.morty.presentation.viewmodel.CharacterState

class CharacterStateParameterProvider : PreviewParameterProvider<CharacterState> {
    override val values: Sequence<CharacterState> = sequenceOf(
        CharacterState(
            characters = listOf(
                Character(
                    id = 1,
                    name = "Rick Sanchez",
                    status = "Alive",
                    species = "Human",
                    type = "",
                    gender = "Male",
                    location = "Earth",
                    image = ""
                ),
                Character(
                    id = 2,
                    name = "Morty Smith",
                    status = "Alive",
                    species = "Human",
                    type = "",
                    gender = "Male",
                    location = "Earth",
                    image = ""
                )
            )
        ),
        CharacterState(isLoading = true),
        CharacterState(error = "Failed to load characters")
    )
}