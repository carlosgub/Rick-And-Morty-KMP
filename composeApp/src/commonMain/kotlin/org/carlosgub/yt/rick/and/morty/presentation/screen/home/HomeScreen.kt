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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavDestination.Companion.hasRoute
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import kotlinx.coroutines.launch
import org.carlosgub.yt.rick.and.morty.presentation.model.NavigationBarItemModel
import org.carlosgub.yt.rick.and.morty.presentation.navigation.Screen
import org.carlosgub.yt.rick.and.morty.presentation.screen.character.CharacterScreen
import org.carlosgub.yt.rick.and.morty.presentation.screen.episode.EpisodeScreen
import org.carlosgub.yt.rick.and.morty.presentation.screen.location.LocationScreen

@Composable
fun HomeScreen() {
    val navController = rememberNavController()
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination
    val snackBarHostState = remember { SnackbarHostState() }
    val scope = rememberCoroutineScope()

    val items = listOf(
        NavigationBarItemModel(
            icon = Icons.Default.Person,
            label = "Personajes",
            route = Screen.Characters
        ),
        NavigationBarItemModel(
            icon = Icons.Default.LocationOn,
            label = "Lugares",
            route = Screen.Locations
        ),
        NavigationBarItemModel(
            icon = Icons.Default.Movie,
            label = "Episodios",
            route = Screen.Episodes
        )
    )

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        containerColor = Color.White,
        snackbarHost = { SnackbarHost(snackBarHostState) },
        bottomBar = {
            NavigationBar {
                items.forEach { item ->
                    NavigationBarItem(
                        selected = currentDestination?.hierarchy?.any { it.hasRoute(item.route::class) } == true,
                        label = { Text(item.label) },
                        onClick = {
                            navController.navigate(item.route) {
                                popUpTo(navController.graph.findStartDestination().id) {
                                    saveState = true
                                }
                                launchSingleTop = true
                                restoreState = true
                            }
                        },
                        icon = {
                            Icon(item.icon, contentDescription = null)
                        }
                    )
                }

            }
        }
    ) { paddingValues ->
        NavHost(
            navController = navController,
            startDestination = Screen.Characters,
            modifier = Modifier.padding(paddingValues)
        ) {
            composable<Screen.Characters> {
                CharacterScreen(showSnackBar = { message ->
                    scope.launch {
                        snackBarHostState.showSnackbar(message)
                    }
                })
            }
            composable<Screen.Locations> {
                LocationScreen()
            }
            composable<Screen.Episodes> {
                EpisodeScreen()
            }
        }
    }
}