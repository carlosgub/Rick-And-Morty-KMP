package org.carlosgub.yt.rick.and.morty.presentation.screen.character.observer

import androidx.compose.runtime.Composable
import org.carlosgub.yt.rick.and.morty.presentation.navigation.LocalNavController
import org.carlosgub.yt.rick.and.morty.presentation.navigation.Screen
import org.carlosgub.yt.rick.and.morty.presentation.viewmodel.character.CharacterSideEffect
import org.carlosgub.yt.rick.and.morty.presentation.viewmodel.character.CharacterViewModel
import org.orbitmvi.orbit.compose.collectSideEffect

import androidx.compose.material3.SnackbarHostState
import kotlinx.coroutines.launch
import androidx.compose.runtime.rememberCoroutineScope

@Composable
fun CharacterObserver(
    viewModel: CharacterViewModel,
    snackbarHostState: SnackbarHostState
) {
    val navController = LocalNavController.current
    val scope = rememberCoroutineScope()

    viewModel.collectSideEffect { sideEffect ->
        when (sideEffect) {
            is CharacterSideEffect.NavigateToCharacterDetail -> {
                navController.navigate(Screen.CharacterDetail(sideEffect.id))
            }
            is CharacterSideEffect.ShowSnackbar -> {
                scope.launch {
                    snackbarHostState.showSnackbar(sideEffect.message)
                }
            }
        }
    }
}