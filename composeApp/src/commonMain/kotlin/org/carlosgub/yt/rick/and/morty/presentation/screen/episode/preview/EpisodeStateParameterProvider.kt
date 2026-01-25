package org.carlosgub.yt.rick.and.morty.presentation.screen.episode.preview

import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import org.carlosgub.yt.rick.and.morty.domain.model.Episode
import org.carlosgub.yt.rick.and.morty.presentation.viewmodel.episode.EpisodeState

class EpisodeStateParameterProvider : PreviewParameterProvider<EpisodeState> {
    override val values: Sequence<EpisodeState> = sequenceOf(
        EpisodeState(
            isLoading = true,
            episodes = emptyList()
        ),
        EpisodeState(
            isLoading = false,
            episodes = listOf(
                Episode(
                    id = 1,
                    name = "Planeta tierra",
                    airDate = "Deciembre",
                    episode = "S01E01",
                ),
                Episode(
                    id = 2,
                    name = "Abadango",
                    airDate = "Deciembre",
                    episode = "S01E02",
                ),
            )
        )

    )

}