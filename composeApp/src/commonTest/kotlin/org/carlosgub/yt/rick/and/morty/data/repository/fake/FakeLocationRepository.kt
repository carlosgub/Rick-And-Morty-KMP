package org.carlosgub.yt.rick.and.morty.data.repository.fake

import org.carlosgub.yt.rick.and.morty.domain.model.Location
import org.carlosgub.yt.rick.and.morty.domain.repository.LocationRepository

class FakeLocationRepository : LocationRepository {

    var locationsResult: Result<List<Location>>? = null

    override suspend fun getLocations(page: Int): Result<List<Location>> {
        return locationsResult ?: Result.failure(Exception("Not initialized"))
    }
}