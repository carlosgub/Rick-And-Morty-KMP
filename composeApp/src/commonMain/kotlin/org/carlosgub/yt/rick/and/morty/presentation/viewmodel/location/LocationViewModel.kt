package org.carlosgub.yt.rick.and.morty.presentation.viewmodel.location

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import org.carlosgub.yt.rick.and.morty.domain.repository.LocationRepository
import org.orbitmvi.orbit.ContainerHost
import org.orbitmvi.orbit.container

class LocationViewModel(
    private val locationRepository: LocationRepository
) : ViewModel(), ContainerHost<LocationState, LocationSideEffect> {

    override val container = viewModelScope.container<LocationState, LocationSideEffect>(
        initialState = LocationState(),
        onCreate = { getLocations() }
    )

    private fun getLocations() = intent {
        reduce { state.copy(isLoading = true) }
        locationRepository.getLocations(1)
            .onSuccess { locations ->
                reduce {
                    state.copy(
                        isLoading = false,
                        locations = locations,
                        errorMessage = null,
                    )
                }
            }
            .onFailure { error ->
                reduce {
                    state.copy(
                        isLoading = false,
                        locations = emptyList(),
                        errorMessage = error.message ?: "Hubo un error",
                    )
                }
            }
    }
}