package org.carlosgub.yt.rick.and.morty.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import org.carlosgub.yt.rick.and.morty.domain.model.Location
import org.carlosgub.yt.rick.and.morty.domain.repository.LocationRepository
import org.orbitmvi.orbit.ContainerHost
import org.orbitmvi.orbit.container

import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.reduce

data class LocationState(
    val locations: List<Location> = emptyList(),
    val isLoading: Boolean = false,
    val error: String? = null
)

sealed class LocationSideEffect

class LocationViewModel(
    private val locationRepository: LocationRepository
) : ViewModel(), ContainerHost<LocationState, LocationSideEffect> {

    override val container = viewModelScope.container<LocationState, LocationSideEffect>(LocationState())

    init {
        getLocations()
    }

    private fun getLocations() = intent {
        if (state.locations.isNotEmpty()) return@intent
        reduce { state.copy(isLoading = true) }
        val locations = locationRepository.getLocations(1)
        reduce {
            state.copy(
                isLoading = false,
                locations = locations
            )
        }
    }
}
