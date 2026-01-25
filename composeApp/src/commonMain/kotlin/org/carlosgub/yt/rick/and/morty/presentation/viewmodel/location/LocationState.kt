package org.carlosgub.yt.rick.and.morty.presentation.viewmodel.location

import org.carlosgub.yt.rick.and.morty.domain.model.Location

data class LocationState(
    val isLoading: Boolean = false,
    val locations: List<Location> = emptyList(),
)