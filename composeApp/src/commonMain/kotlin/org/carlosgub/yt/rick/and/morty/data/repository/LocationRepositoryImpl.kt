package org.carlosgub.yt.rick.and.morty.data.repository

import org.carlosgub.yt.rick.and.morty.data.mapper.toLocation
import org.carlosgub.yt.rick.and.morty.data.remote.RickAndMortyApi
import org.carlosgub.yt.rick.and.morty.domain.model.Location
import org.carlosgub.yt.rick.and.morty.domain.repository.LocationRepository

class LocationRepositoryImpl(
    private val api: RickAndMortyApi,
) : LocationRepository {

    override suspend fun getLocation(page: Int): List<Location> {
        return api.getLocation(page).results.map { it.toLocation() }
    }
}