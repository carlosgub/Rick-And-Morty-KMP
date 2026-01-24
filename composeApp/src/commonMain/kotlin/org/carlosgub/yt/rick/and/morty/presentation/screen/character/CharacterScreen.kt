package org.carlosgub.yt.rick.and.morty.presentation.screen.character

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import org.carlosgub.yt.rick.and.morty.domain.model.Character
import org.carlosgub.yt.rick.and.morty.presentation.screen.character.content.CharacterItem
import org.carlosgub.yt.rick.and.morty.presentation.viewmodel.character.CharacterViewModel
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun CharacterScreen() {
    val viewModel = koinViewModel<CharacterViewModel>()
    val state = viewModel.container.stateFlow.collectAsStateWithLifecycle().value



    Box(
        modifier = Modifier.fillMaxSize(),
    ) {
        if (state.isLoading) {
            CircularProgressIndicator(
                modifier = Modifier.align(Alignment.Center)
            )
        }
        if (state.characters.isNotEmpty()) {
            LazyColumn(
                modifier = Modifier.fillMaxSize(),
                contentPadding = PaddingValues(16.dp),
                verticalArrangement = Arrangement.spacedBy(6.dp)
            ) {
                items(
                    items = state.characters,
                    key = { it.id },
                ) { character ->
                    CharacterItem(character = character)
                }
            }
        }
    }
}

