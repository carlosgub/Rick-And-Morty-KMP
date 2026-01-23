package org.carlosgub.yt.rick.and.morty.domain.repository

import org.carlosgub.yt.rick.and.morty.domain.model.Character

interface CharacterRepository {
    suspend fun getCharacters(page:Int):List<Character>
}