package org.carlosgub.yt.rick.and.morty.presentation.screen.character.preview

import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import org.carlosgub.yt.rick.and.morty.domain.model.Character
import org.carlosgub.yt.rick.and.morty.presentation.viewmodel.character.CharacterState

class CharacterStateParameterProvider : PreviewParameterProvider<CharacterState> {
    override val values: Sequence<CharacterState> = sequenceOf(
        CharacterState(
            isLoading = true,
            characters = emptyList(),
        ),
        CharacterState(
            isLoading = false,
            characters = emptyList(),
            errorMessage = "Hubo un error",
        ),
        CharacterState(
            isLoading = false,
            characters = listOf(
                Character(
                    id = 1,
                    name = "Rick Sanchez",
                    status = "Alive",
                    species = "Humano",
                    type = "",
                    gender = "Male",
                    location = "Earth",
                    image = "",
                ),
                Character(
                    id = 2,
                    name = "Morty Smith",
                    status = "Alive",
                    species = "Humano",
                    type = "",
                    gender = "Male",
                    location = "Earth",
                    image = "",
                ),
            ),
        ),
        CharacterState(
            isLoading = false,
            characters = listOf(
                Character(
                    id = 1,
                    name = "Rick Sanchez",
                    status = "Alive",
                    species = "Humano",
                    type = "",
                    gender = "Male",
                    location = "Earth",
                    image = "",
                ),
                Character(
                    id = 2,
                    name = "Morty Smith",
                    status = "Alive",
                    species = "Humano",
                    type = "",
                    gender = "Male",
                    location = "Earth",
                    image = "",
                ),
            ),
            isLoadingNextPage = true,
        ),
    )
}
