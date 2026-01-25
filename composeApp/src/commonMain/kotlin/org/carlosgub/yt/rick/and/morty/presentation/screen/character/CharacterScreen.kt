package org.carlosgub.yt.rick.and.morty.presentation.screen.character

import androidx.compose.foundation.background
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import org.carlosgub.yt.rick.and.morty.presentation.navigation.LocalNavController
import org.carlosgub.yt.rick.and.morty.presentation.navigation.Screen
import org.carlosgub.yt.rick.and.morty.presentation.screen.character.content.CharacterItem
import org.carlosgub.yt.rick.and.morty.presentation.screen.character.observer.CharacterObserver
import org.carlosgub.yt.rick.and.morty.presentation.screen.character.preview.CharacterStateParameterProvider
import org.carlosgub.yt.rick.and.morty.presentation.viewmodel.character.CharacterSideEffect
import org.carlosgub.yt.rick.and.morty.presentation.viewmodel.character.CharacterState
import org.carlosgub.yt.rick.and.morty.presentation.viewmodel.character.CharacterViewModel
import org.koin.compose.viewmodel.koinViewModel
import org.orbitmvi.orbit.compose.collectSideEffect

@Composable
fun CharacterScreen() {
    val viewModel = koinViewModel<CharacterViewModel>()
    val state = viewModel.container.stateFlow.collectAsStateWithLifecycle().value

    CharacterObserver(viewModel = viewModel)
    CharacterContent(
        state = state,
        onCharacterClicked = viewModel::onCharacterClicked
    )
}

@Composable
private fun CharacterContent(
    state: CharacterState,
    onCharacterClicked: (Int) -> Unit
) {
    Box(
        modifier = Modifier.fillMaxSize()
            .background(Color.White),
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
                    CharacterItem(
                        character = character,
                        onClick = onCharacterClicked
                    )
                }
            }
        }
    }
}

@Preview
@Composable
private fun CharacterContentPreview(
    @PreviewParameter(CharacterStateParameterProvider::class) state: CharacterState,
) {
    CharacterContent(
        state = state,
        onCharacterClicked = {}
    )
}

