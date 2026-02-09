package org.carlosgub.yt.rick.and.morty.data.repository

import kotlinx.coroutines.test.runTest
import org.carlosgub.yt.rick.and.morty.data.remote.fake.RickAndMortyApiFake
import org.carlosgub.yt.rick.and.morty.data.testdata.TestData.characterData
import org.carlosgub.yt.rick.and.morty.data.testdata.TestData.characterResponse
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue

class CharacterRepositoryImplTest {
    private val api = RickAndMortyApiFake()
    private val repository = CharacterRepositoryImpl(api)

    @Test
    fun `getCharacters return success result`() =
        runTest {
            api.charactersResult = Result.success(characterResponse)

            val result = repository.getCharacters(1)

            assertTrue(result.isSuccess)
            result.onSuccess { characterPaging ->
                assertEquals(1, characterPaging.characters.size)
                assertEquals("Rick Sanchez", characterPaging.characters.first().name)
                assertEquals(false, characterPaging.canLoadMore)
            }
        }

    @Test
    fun `getCharacters return failure when api fails`() =
        runTest {
            val exception = Exception("Network error")
            api.charactersResult = Result.failure(exception)

            val result = repository.getCharacters(1)

            assertTrue(result.isFailure)
            result.onFailure { error ->
                assertEquals(exception.message, error.message.orEmpty())
            }
        }

    @Test
    fun `getCharacter return success result`() =
        runTest {
            api.characterResult = Result.success(characterData)

            val result = repository.getCharacter(1)

            assertTrue(result.isSuccess)
            result.onSuccess { character ->
                assertEquals(1, character.id)
                assertEquals("Rick Sanchez", character.name)
            }
        }
}
