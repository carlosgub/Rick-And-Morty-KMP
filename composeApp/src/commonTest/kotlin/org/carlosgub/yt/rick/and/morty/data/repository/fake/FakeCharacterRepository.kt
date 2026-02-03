package org.carlosgub.yt.rick.and.morty.data.repository.fake

import org.carlosgub.yt.rick.and.morty.domain.model.Character
import org.carlosgub.yt.rick.and.morty.domain.model.CharacterPaging
import org.carlosgub.yt.rick.and.morty.domain.repository.CharacterRepository

class FakeCharacterRepository : CharacterRepository {

    var charactersResult: Result<CharacterPaging>? = null
    var characterResult: Result<Character>? = null

    override suspend fun getCharacters(page: Int): Result<CharacterPaging> {
        return charactersResult ?: Result.failure(Exception("Not initialized"))
    }

    override suspend fun getCharacter(id: Int): Result<Character> {
        return characterResult ?: Result.failure(Exception("Not initialized"))
    }
}