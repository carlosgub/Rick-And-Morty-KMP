package org.carlosgub.yt.rick.and.morty.domain.repository

import org.carlosgub.yt.rick.and.morty.domain.model.Location

interface LocationRepository {
    suspend fun getLocation(page:Int):List<Location>
}