package org.carlosgub.yt.rick.and.morty.presentation.screen.character

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import org.carlosgub.yt.rick.and.morty.presentation.screen.character.content.CharacterItem
import org.carlosgub.yt.rick.and.morty.presentation.screen.character.preview.CharacterStateParameterProvider
import org.carlosgub.yt.rick.and.morty.presentation.viewmodel.CharacterState
import org.carlosgub.yt.rick.and.morty.presentation.viewmodel.CharacterViewModel
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun CharacterScreen() {
    val viewModel = koinViewModel<CharacterViewModel>()
    val state by viewModel.container.stateFlow.collectAsStateWithLifecycle()
    CharacterContent(state = state)
}

@Composable
fun CharacterContent(
    state: CharacterState
) {
    Box(
        modifier = Modifier.fillMaxSize()
            .background(Color.White),
        contentAlignment = Alignment.Center
    ) {
        if (state.isLoading) {
            CircularProgressIndicator(
                color = MaterialTheme.colorScheme.primary
            )
        } else if (state.error != null) {
            Text(
                text = "Error: ${state.error}",
                color = MaterialTheme.colorScheme.error,
                style = MaterialTheme.typography.bodyLarge
            )
        } else {
            LazyColumn(
                modifier = Modifier.fillMaxSize(),
                contentPadding = PaddingValues(16.dp),
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                item {
                    Text(
                        text = "Characters",
                        style = MaterialTheme.typography.headlineMedium,
                        modifier = Modifier.padding(bottom = 16.dp)
                    )
                }
                items(
                    items = state.characters,
                    key = { it.id }
                ) { character ->
                    CharacterItem(character = character)
                }
            }
        }
    }
}

@Preview
@Composable
fun CharacterScreenPreview(
    @PreviewParameter(CharacterStateParameterProvider::class) state: CharacterState
) {
    MaterialTheme {
        CharacterContent(state = state)
    }
}