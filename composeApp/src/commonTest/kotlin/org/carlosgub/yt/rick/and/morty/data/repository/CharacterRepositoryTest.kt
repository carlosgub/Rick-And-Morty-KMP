package org.carlosgub.yt.rick.and.morty.data.repository

import kotlinx.coroutines.test.runTest
import org.carlosgub.yt.rick.and.morty.data.remote.FakeRickAndMortyApi
import org.carlosgub.yt.rick.and.morty.data.testdata.TestData
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue

class CharacterRepositoryTest {

    private val api = FakeRickAndMortyApi()
    private val repository = CharacterRepositoryImpl(api)

    @Test
    fun `getCharacters returns success result with mapped data`() = runTest {
        api.charactersResult = Result.success(TestData.characterResponse)

        val result = repository.getCharacters(1)

        assertTrue(result.isSuccess)
        result.onSuccess { paging ->
            assertEquals(1, paging.characters.size)
            assertEquals("Rick Sanchez", paging.characters.first().name)
            assertEquals(paging.canLoadMore, true)
        }
    }

    @Test
    fun `getCharacters returns failure when api fails`() = runTest {
        val exception = Exception("Network error")
        api.charactersResult = Result.failure(exception)

        val result = repository.getCharacters(1)

        assertTrue(result.isFailure)
        assertEquals(exception, result.exceptionOrNull())
    }

    @Test
    fun `getCharacter returns success result with mapped data`() = runTest {
        api.characterResult = Result.success(TestData.characterData)

        val result = repository.getCharacter(1)

        assertTrue(result.isSuccess)
        result.onSuccess { character ->
            assertEquals(1, character.id)
            assertEquals("Rick Sanchez", character.name)
        }
    }
}
