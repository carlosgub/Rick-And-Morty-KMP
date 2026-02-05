package org.carlosgub.yt.rick.and.morty.data.repository.fake

import org.carlosgub.yt.rick.and.morty.domain.model.Character
import org.carlosgub.yt.rick.and.morty.domain.model.CharacterPaging
import org.carlosgub.yt.rick.and.morty.domain.repository.CharacterRepository

class CharacterRepositoryFake : CharacterRepository {

    var charactersResult: Result<CharacterPaging>? = null
    var characterResult: Result<Character>? = null

    override suspend fun getCharacters(page: Int): Result<CharacterPaging> {
        return charactersResult ?: Result.failure(Exception("No se inicializo este fake"))
    }

    override suspend fun getCharacter(id: Int): Result<Character> {
        return characterResult ?: Result.failure(Exception("No se inicializo este fake"))
    }
}