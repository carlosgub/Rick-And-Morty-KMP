package org.carlosgub.yt.rick.and.morty.presentation.screen.characterdetail.observer

import androidx.compose.runtime.Composable
import org.carlosgub.yt.rick.and.morty.presentation.navigation.LocalNavController
import org.carlosgub.yt.rick.and.morty.presentation.viewmodel.characterdetail.CharacterDetailSideEffect
import org.carlosgub.yt.rick.and.morty.presentation.viewmodel.characterdetail.CharacterDetailViewModel
import org.orbitmvi.orbit.compose.collectSideEffect

@Composable
fun CharacterDetailObserver(viewModel: CharacterDetailViewModel){
    val navController = LocalNavController.current

    viewModel.collectSideEffect { sideEffect ->
        when (sideEffect){
            CharacterDetailSideEffect.NavigateBack -> navController.popBackStack()
        }
    }
}