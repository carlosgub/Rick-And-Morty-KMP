package org.carlosgub.yt.rick.and.morty.presentation.screen.location.preview

import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import org.carlosgub.yt.rick.and.morty.domain.model.Character
import org.carlosgub.yt.rick.and.morty.domain.model.Location
import org.carlosgub.yt.rick.and.morty.presentation.viewmodel.character.CharacterState
import org.carlosgub.yt.rick.and.morty.presentation.viewmodel.location.LocationState

class LocationStateParameterProvider : PreviewParameterProvider<LocationState> {
    override val values: Sequence<LocationState> = sequenceOf(
        LocationState(
            isLoading = true,
            locations = emptyList()
        ),
        LocationState(
            isLoading = false,
            locations = listOf(
                Location(
                    id = 1,
                    name = "Planeta tierra",
                    type = "tierra",
                    dimension = "Dimension C-137",
                ),
                Location(
                    id = 2,
                    name = "Abadango",
                    type = "Cluster",
                    dimension = "Desconocida",
                ),
                Location(
                    id = 3,
                    name = "Abadangasdasdasdasdasdso 12312312312312",
                    type = "Cluster asdasdasdasdasdasdasd  123123123123",
                    dimension = "Desconocida asdasdasdasdasd 123123123123123123",
                ),
            )
        )

    )

}