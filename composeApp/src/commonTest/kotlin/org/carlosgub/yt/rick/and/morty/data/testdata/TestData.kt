package org.carlosgub.yt.rick.and.morty.data.testdata

import org.carlosgub.yt.rick.and.morty.data.model.CharacterResponse
import org.carlosgub.yt.rick.and.morty.data.model.EpisodeResponse
import org.carlosgub.yt.rick.and.morty.data.model.InfoResponse
import org.carlosgub.yt.rick.and.morty.data.model.LocationResponse

object TestData {
    val info = InfoResponse(
        count = 1,
        pages = 1,
        next = null,
        prev = null
    )

    val characterData = CharacterResponse.CharacterData(
        id = 1,
        name = "Rick Sanchez",
        status = "Alive",
        species = "Human",
        type = "",
        gender = "Male",
        origin = CharacterResponse.CharacterData.LocationData(
            name = "Earth",
            url = ""
        ),
        location = CharacterResponse.CharacterData.LocationData(
            name = "Earth",
            url = ""
        ),
        image = "",
        episode = listOf(),
        url = "",
        created = "",
    )

    val characterResponse = CharacterResponse(
        info = info,
        results = listOf(characterData)
    )

    val locationResponse = LocationResponse(
        info = info,
        results = listOf(
            LocationResponse.LocationData(
                id = 1,
                name = "Earth",
                type = "Planet",
                dimension = "",
            )
        )
    )

    val episodeResponse = EpisodeResponse(
        info = info,
        results = listOf(
            EpisodeResponse.EpisodeData(
                id = 1,
                name = "Pilot",
                airDate = "",
                episode = "S0102",
            )
        )
    )
}