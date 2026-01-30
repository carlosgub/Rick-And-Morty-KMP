package org.carlosgub.yt.rick.and.morty.presentation.screen.characterdetail

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import org.carlosgub.yt.rick.and.morty.presentation.screen.characterdetail.content.CharacterDetailContent
import org.carlosgub.yt.rick.and.morty.presentation.screen.characterdetail.observer.CharacterDetailObserver
import org.carlosgub.yt.rick.and.morty.presentation.viewmodel.characterdetail.CharacterDetailViewModel
import org.koin.compose.viewmodel.koinViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CharacterDetailScreen() {
    val viewModel = koinViewModel<CharacterDetailViewModel>()
    val state = viewModel.container.stateFlow.collectAsStateWithLifecycle().value

    CharacterDetailObserver(viewModel)

    CharacterDetailContent(
        state = state,
        onNavigateBack = viewModel::navigateBack
    )
}