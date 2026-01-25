package org.carlosgub.yt.rick.and.morty.presentation.screen.episode.content

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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import org.carlosgub.yt.rick.and.morty.presentation.screen.episode.preview.EpisodeStateParameterProvider
import org.carlosgub.yt.rick.and.morty.presentation.viewmodel.episode.EpisodeState

@Composable
internal fun EpisodeContent(state: EpisodeState) {
    Box(
        modifier = Modifier.fillMaxSize()
            .background(Color.White),
    ) {
        if (state.isLoading) {
            CircularProgressIndicator(
                modifier = Modifier.align(Alignment.Center)
            )
        }
        if (state.episodes.isNotEmpty()) {
            LazyColumn(
                modifier = Modifier.fillMaxSize(),
                contentPadding = PaddingValues(16.dp),
                verticalArrangement = Arrangement.spacedBy(6.dp)
            ) {
                state.episodesPerSeason.forEach { (season, episodes) ->
                    item {
                        val seasonHeader = when (season) {
                            "S01" -> "Season 1"
                            "S02" -> "Season 2"
                            "S03" -> "Season 3"
                            "S04" -> "Season 4"
                            "S05" -> "Season 5"
                            "S06" -> "Season 6"
                            else -> season
                        }

                        Text(
                            text = seasonHeader,
                            color = MaterialTheme.colorScheme.primary,
                            fontWeight = FontWeight.SemiBold,
                            fontSize = 22.sp,
                            modifier = Modifier.padding(vertical = 12.dp, horizontal = 4.dp)
                        )
                    }
                    items(
                        items = episodes,
                        key = { it.id },
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
private fun EpisodeContentPreview(
    @PreviewParameter(EpisodeStateParameterProvider::class) state: EpisodeState,
) {
    EpisodeContent(
        state = state
    )
}