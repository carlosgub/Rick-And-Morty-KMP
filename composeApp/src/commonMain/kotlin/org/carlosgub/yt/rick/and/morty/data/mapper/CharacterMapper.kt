package org.carlosgub.yt.rick.and.morty.data.mapper

import org.carlosgub.yt.rick.and.morty.data.model.CharacterResponse
import org.carlosgub.yt.rick.and.morty.domain.model.Character

fun CharacterResponse.CharacterData.toCharacter(): Character {
    return Character(
        id = id,
        name = name,
        status = status,
        species = species,
        type = type,
        gender = gender,
        location = location.name,
        image = image,
    )
}