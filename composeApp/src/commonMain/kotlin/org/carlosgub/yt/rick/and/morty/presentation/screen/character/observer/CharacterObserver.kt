package org.carlosgub.yt.rick.and.morty.presentation.screen.character.observer

import androidx.compose.runtime.Composable
import org.carlosgub.yt.rick.and.morty.presentation.navigation.LocalNavController
import org.carlosgub.yt.rick.and.morty.presentation.navigation.Screen
import org.carlosgub.yt.rick.and.morty.presentation.navigation.Screen.*
import org.carlosgub.yt.rick.and.morty.presentation.viewmodel.character.CharacterSideEffect
import org.carlosgub.yt.rick.and.morty.presentation.viewmodel.character.CharacterViewModel
import org.orbitmvi.orbit.compose.collectSideEffect

@Composable
fun CharacterObserver(
    viewModel: CharacterViewModel,
    showSnackBar: (String) -> Unit) {
    val navController = LocalNavController.current

    viewModel.collectSideEffect { sideEffect ->
        when (sideEffect) {
            is CharacterSideEffect.NavigateToCharacterDetail -> {
                navController.navigate(CharacterDetail(sideEffect.id))
            }

            is CharacterSideEffect.ShowSnackBar -> showSnackBar(sideEffect.message)
        }
    }
}