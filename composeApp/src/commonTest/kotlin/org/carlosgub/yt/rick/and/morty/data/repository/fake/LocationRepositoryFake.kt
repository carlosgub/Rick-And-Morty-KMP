package org.carlosgub.yt.rick.and.morty.data.repository.fake

import org.carlosgub.yt.rick.and.morty.domain.model.Location
import org.carlosgub.yt.rick.and.morty.domain.repository.LocationRepository

class LocationRepositoryFake : LocationRepository {
    var locationResult: Result<List<Location>>? = null

    override suspend fun getLocations(page: Int): Result<List<Location>> =
        locationResult ?: Result.failure(Exception("No se inicializo este fake"))
}
