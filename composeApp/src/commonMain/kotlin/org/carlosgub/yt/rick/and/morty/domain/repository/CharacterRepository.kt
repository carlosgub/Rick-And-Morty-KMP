package org.carlosgub.yt.rick.and.morty.domain.repository

import org.carlosgub.yt.rick.and.morty.domain.model.Character
import org.carlosgub.yt.rick.and.morty.domain.model.CharacterPaging

interface CharacterRepository {
    suspend fun getCharacters(page: Int): CharacterPaging

    suspend fun getCharacter(id: Int): Character
}