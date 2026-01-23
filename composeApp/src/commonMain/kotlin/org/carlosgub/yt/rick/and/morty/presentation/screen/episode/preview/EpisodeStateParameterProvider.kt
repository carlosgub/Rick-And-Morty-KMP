package org.carlosgub.yt.rick.and.morty.presentation.screen.episode.preview

import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import org.carlosgub.yt.rick.and.morty.domain.model.Episode
import org.carlosgub.yt.rick.and.morty.presentation.viewmodel.EpisodeState

class EpisodeStateParameterProvider : PreviewParameterProvider<EpisodeState> {
    override val values = sequenceOf(
        EpisodeState(
            episodes = listOf(
                Episode(
                    id = 1,
                    name = "Pilot",
                    airDate = "December 2, 2013",
                    episode = "S01E01"
                ),
                Episode(
                    id = 2,
                    name = "Lawnmower Dog",
                    airDate = "December 9, 2013",
                    episode = "S01E02"
                ),
                Episode(
                    id = 3,
                    name = "Anatomy Park",
                    airDate = "December 16, 2013",
                    episode = "S01E03"
                )
            )
        ),
        EpisodeState(isLoading = true),
        EpisodeState(error = "Error loading episodes")
    )
}
