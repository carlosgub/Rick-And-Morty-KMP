package org.carlosgub.yt.rick.and.morty.data.repository

import org.carlosgub.yt.rick.and.morty.data.mapper.toDomain
import org.carlosgub.yt.rick.and.morty.data.remote.RickAndMortyApi
import org.carlosgub.yt.rick.and.morty.domain.model.Character
import org.carlosgub.yt.rick.and.morty.domain.repository.CharacterRepository

class CharacterRepositoryImpl(
    private val api: RickAndMortyApi
) : CharacterRepository {
    override suspend fun getCharacters(page: Int): List<Character> {
        return api.getCharacters(page).results.map { it.toDomain() }
    }
}
