package org.carlosgub.yt.rick.and.morty.presentation.screen.episode

import androidx.compose.runtime.Composable
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import org.carlosgub.yt.rick.and.morty.presentation.screen.episode.content.EpisodeContent
import org.carlosgub.yt.rick.and.morty.presentation.viewmodel.episode.EpisodeViewModel
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun EpisodeScreen() {
    val viewModel = koinViewModel<EpisodeViewModel>()
    val state = viewModel.container.stateFlow.collectAsStateWithLifecycle().value

    EpisodeContent(state)
}