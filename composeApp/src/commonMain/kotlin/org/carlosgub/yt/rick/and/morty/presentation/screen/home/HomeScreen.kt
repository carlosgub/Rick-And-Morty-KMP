package org.carlosgub.yt.rick.and.morty.presentation.screen.home

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.Movie
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.navigation3.runtime.NavEntry
import androidx.navigation3.ui.NavDisplay
import kotlinx.coroutines.launch
import org.carlosgub.yt.rick.and.morty.presentation.model.NavigationBarItemModel
import org.carlosgub.yt.rick.and.morty.presentation.navigation.Screen
import org.carlosgub.yt.rick.and.morty.presentation.screen.character.CharacterScreen
import org.carlosgub.yt.rick.and.morty.presentation.screen.episode.EpisodeScreen
import org.carlosgub.yt.rick.and.morty.presentation.screen.location.LocationScreen
import org.jetbrains.compose.resources.stringResource
import rickandmortykmp.composeapp.generated.resources.Res
import rickandmortykmp.composeapp.generated.resources.home_screen_characters
import rickandmortykmp.composeapp.generated.resources.home_screen_episodes
import rickandmortykmp.composeapp.generated.resources.home_screen_locations

@Composable
fun HomeScreen(modifier: Modifier = Modifier) {
    val backStack = remember { mutableStateListOf<Any>(Screen.Characters) }
    val currentDestination = backStack.lastOrNull()

    val snackBarHostState = remember { SnackbarHostState() }
    val scope = rememberCoroutineScope()

    val items = listOf(
        NavigationBarItemModel(
            icon = Icons.Default.Person,
            label = stringResource(Res.string.home_screen_characters),
            route = Screen.Characters,
        ),
        NavigationBarItemModel(
            icon = Icons.Default.LocationOn,
            label = stringResource(Res.string.home_screen_locations),
            route = Screen.Locations,
        ),
        NavigationBarItemModel(
            icon = Icons.Default.Movie,
            label = stringResource(Res.string.home_screen_episodes),
            route = Screen.Episodes,
        ),
    )

    Scaffold(
        modifier = modifier.fillMaxSize(),
        containerColor = Color.White,
        snackbarHost = { SnackbarHost(snackBarHostState) },
        bottomBar = {
            NavigationBar {
                items.forEach { item ->
                    NavigationBarItem(
                        selected = currentDestination?.let { it::class == item.route::class } == true,
                        label = { Text(item.label) },
                        onClick = {
                            if (currentDestination != item.route) {
                                while (backStack.size > 1) {
                                    backStack.removeAt(backStack.size - 1)
                                }

                                if (item.route != Screen.Characters) {
                                    backStack.add(item.route)
                                }
                            }
                        },
                        icon = {
                            Icon(item.icon, contentDescription = null)
                        },
                    )
                }
            }
        },
    ) { paddingValues ->
        NavDisplay(
            backStack = backStack,
            modifier = Modifier.padding(paddingValues),
        ) { key ->
            NavEntry(key) {
                when (key) {
                    is Screen.Characters -> {
                        CharacterScreen(
                            showSnackBar = { message ->
                                scope.launch {
                                    snackBarHostState.showSnackbar(message)
                                }
                            },
                        )
                    }

                    is Screen.Locations -> LocationScreen()
                    is Screen.Episodes -> EpisodeScreen()
                }
            }
        }
    }
}
