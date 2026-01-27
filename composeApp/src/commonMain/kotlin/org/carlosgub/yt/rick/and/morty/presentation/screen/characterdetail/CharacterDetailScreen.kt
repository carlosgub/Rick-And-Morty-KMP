package org.carlosgub.yt.rick.and.morty.presentation.screen.characterdetail

import androidx.compose.runtime.Composable
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import org.carlosgub.yt.rick.and.morty.presentation.screen.characterdetail.content.CharacterDetailContent
import org.carlosgub.yt.rick.and.morty.presentation.screen.characterdetail.observer.CharacterDetailObserver
import org.carlosgub.yt.rick.and.morty.presentation.viewmodel.characterdetail.CharacterDetailViewModel
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun CharacterDetailScreen() {
    val viewModel = koinViewModel<CharacterDetailViewModel>()
    val state = viewModel.container.stateFlow.collectAsStateWithLifecycle().value

    CharacterDetailObserver(viewModel)

    CharacterDetailContent(
        state = state,
        onBackClick = viewModel::navigateBack
    )
}