package org.carlosgub.yt.rick.and.morty.presentation.screen.location.preview

import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import org.carlosgub.yt.rick.and.morty.domain.model.Location
import org.carlosgub.yt.rick.and.morty.presentation.viewmodel.LocationState

class LocationStateParameterProvider : PreviewParameterProvider<LocationState> {
    override val values = sequenceOf(
        LocationState(
            locations = listOf(
                Location(
                    id = 1,
                    name = "Earth (C-137)",
                    type = "Planet",
                    dimension = "Dimension C-137"
                ),
                Location(
                    id = 2,
                    name = "Abadango",
                    type = "Cluster",
                    dimension = "unknown"
                ),
                Location(
                    id = 3,
                    name = "Citadel of Ricks",
                    type = "Space station",
                    dimension = "unknown"
                )
            )
        ),
        LocationState(isLoading = true),
        LocationState(error = "Error loading locations")
    )
}
