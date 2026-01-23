package org.carlosgub.yt.rick.and.morty.presentation.screen.episode

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items


import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember

import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight

import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import org.carlosgub.yt.rick.and.morty.presentation.screen.episode.content.EpisodeItem
import org.carlosgub.yt.rick.and.morty.presentation.screen.episode.preview.EpisodeStateParameterProvider
import org.carlosgub.yt.rick.and.morty.presentation.viewmodel.EpisodeState
import org.carlosgub.yt.rick.and.morty.presentation.viewmodel.EpisodeViewModel
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun EpisodeScreen() {
    val viewModel = koinViewModel<EpisodeViewModel>()
    val state by viewModel.container.stateFlow.collectAsStateWithLifecycle()
    EpisodeContent(state = state)
}

@Composable
fun EpisodeContent(
    state: EpisodeState
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
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
                        text = "Episodes",
                        style = MaterialTheme.typography.headlineMedium,
                        modifier = Modifier.padding(bottom = 16.dp)
                    )
                }

                state.episodesBySeason.forEach { (season, episodes) ->
                    item {
                        val seasonFormatted = when (season) {
                            "S01" -> "Season 1"
                            "S02" -> "Season 2"
                            "S03" -> "Season 3"
                            "S04" -> "Season 4"
                            "S05" -> "Season 5"
                            else -> season
                        }
                        Text(
                            text = seasonFormatted,
                            style = MaterialTheme.typography.titleLarge.copy(
                                fontWeight = FontWeight.Bold,
                                color = MaterialTheme.colorScheme.primary
                            ),
                            modifier = Modifier.padding(vertical = 8.dp)
                        )
                    }

                    items(
                        items = episodes,
                        key = { it.id }
                    ) { episode ->
                        EpisodeItem(episode = episode)
                    }
                }
            }
        }

    }
}


@Preview
@Composable
fun EpisodeScreenPreview(
    @PreviewParameter(EpisodeStateParameterProvider::class) state: EpisodeState
) {
    MaterialTheme {
        EpisodeContent(state = state)
    }
}
