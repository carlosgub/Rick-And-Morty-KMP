package org.carlosgub.yt.rick.and.morty.data.mapper

import org.carlosgub.yt.rick.and.morty.data.model.LocationResponse
import org.carlosgub.yt.rick.and.morty.domain.model.Location

fun LocationResponse.LocationData.toLocation(): Location {
    return Location(
        id = id,
        name = name,
        type = type,
        dimension = dimension,
    )
}