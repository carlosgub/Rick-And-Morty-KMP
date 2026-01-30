package org.carlosgub.yt.rick.and.morty.data.repository

import org.carlosgub.yt.rick.and.morty.data.mapper.toCharacter
import org.carlosgub.yt.rick.and.morty.data.remote.RickAndMortyApi
import org.carlosgub.yt.rick.and.morty.domain.model.Character
import org.carlosgub.yt.rick.and.morty.domain.model.CharacterPaging
import org.carlosgub.yt.rick.and.morty.domain.repository.CharacterRepository

class CharacterRepositoryImpl(
    private val api: RickAndMortyApi,
) : CharacterRepository {
    override suspend fun getCharacters(page: Int): Result<CharacterPaging> {
        return api.getCharacters(page).map { response ->
            CharacterPaging(
                characters = response.results.map {
                    it.toCharacter()
                },
                canLoadMore = response.info.next != null
            )
        }
    }

    override suspend fun getCharacter(id: Int): Character =
        api.getCharacter(id).toCharacter()
}