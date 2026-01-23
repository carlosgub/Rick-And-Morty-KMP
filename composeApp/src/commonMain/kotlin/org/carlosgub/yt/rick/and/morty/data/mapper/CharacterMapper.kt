package org.carlosgub.yt.rick.and.morty.data.mapper

import org.carlosgub.yt.rick.and.morty.data.model.CharacterResponse
import org.carlosgub.yt.rick.and.morty.domain.model.Character

fun CharacterResponse.CharacterData.toDomain(): Character {
    return Character(
        id = id,
        name = name,
        status = status,
        species = species,
        gender = gender,
        image = image,
        origin = origin.name,
        location = location.name
    )
}
