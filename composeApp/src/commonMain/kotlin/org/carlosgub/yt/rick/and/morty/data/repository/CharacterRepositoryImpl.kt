package org.carlosgub.yt.rick.and.morty.data.repository

import org.carlosgub.yt.rick.and.morty.data.mapper.toCharacter
import org.carlosgub.yt.rick.and.morty.data.remote.RickAndMortyApi
import org.carlosgub.yt.rick.and.morty.domain.model.Character
import org.carlosgub.yt.rick.and.morty.domain.repository.CharacterRepository

class CharacterRepositoryImpl(
    private val api: RickAndMortyApi,
) : CharacterRepository {
    override suspend fun getCharacters(page: Int): List<Character> =
        api.getCharacters(page).results.map { it.toCharacter() }
}