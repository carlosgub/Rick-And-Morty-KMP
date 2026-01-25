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
        LocationState()
    )

    init {
        getLocations()
    }

    private fun getLocations() = intent{
        reduce { state.copy(isLoading = true) }
        val location = locationRepository.getLocations(1)
        reduce {
            state.copy(
                isLoading = false,
                locations = location
            )
        }
    }
}