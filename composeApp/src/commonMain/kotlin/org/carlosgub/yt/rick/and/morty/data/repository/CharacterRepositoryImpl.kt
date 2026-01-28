package org.carlosgub.yt.rick.and.morty.data.repository

import org.carlosgub.yt.rick.and.morty.data.mapper.toCharacter
import org.carlosgub.yt.rick.and.morty.data.remote.RickAndMortyApi
import org.carlosgub.yt.rick.and.morty.domain.model.Character
import org.carlosgub.yt.rick.and.morty.domain.model.CharacterPaging
import org.carlosgub.yt.rick.and.morty.domain.repository.CharacterRepository

class CharacterRepositoryImpl(
    private val api: RickAndMortyApi,
) : CharacterRepository {
    override suspend fun getCharacters(page: Int): CharacterPaging {
        val getCharacters = api.getCharacters(page)
        return CharacterPaging(
            characters = getCharacters.results.map {
                it.toCharacter()
            },
            canLoadMore = getCharacters.info.next != null
        )
    }

    override suspend fun getCharacter(id: Int): Character =
        api.getCharacter(id).toCharacter()
}