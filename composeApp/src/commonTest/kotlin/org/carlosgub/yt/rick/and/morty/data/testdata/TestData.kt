package org.carlosgub.yt.rick.and.morty.data.testdata

import org.carlosgub.yt.rick.and.morty.data.model.CharacterResponse
import org.carlosgub.yt.rick.and.morty.data.model.EpisodeResponse
import org.carlosgub.yt.rick.and.morty.data.model.InfoResponse
import org.carlosgub.yt.rick.and.morty.data.model.LocationResponse

object TestData {
    val characterData = CharacterResponse.CharacterData(
        id = 1,
        name = "Rick Sanchez",
        image = "url",
        status = "Alive",
        species = "Human",
        type = "",
        gender = "Male",
        origin = CharacterResponse.CharacterData.LocationData(name = "Earth", url = "url"),
        location = CharacterResponse.CharacterData.LocationData(name = "Earth", url = "url"),
        episode = emptyList(),
        url = "url",
        created = "created"
    )

    val characterResponse = CharacterResponse(
        info = InfoResponse(count = 1, pages = 1, next = "next_url", prev = null),
        results = listOf(characterData)
    )

    val locationData = LocationResponse.LocationData(
        id = 1,
        name = "Earth",
        type = "Planet",
        dimension = "Dimension C-137"
    )

    val locationResponse = LocationResponse(
        info = InfoResponse(count = 1, pages = 1, next = null, prev = null),
        results = listOf(locationData)
    )

    val episodeData = EpisodeResponse.EpisodeData(
        id = 1,
        name = "Pilot",
        airDate = "December 2, 2013",
        episode = "S01E01"
    )

    val episodeResponse = EpisodeResponse(
        info = InfoResponse(count = 1, pages = 1, next = null, prev = null),
        results = listOf(episodeData)
    )
}
