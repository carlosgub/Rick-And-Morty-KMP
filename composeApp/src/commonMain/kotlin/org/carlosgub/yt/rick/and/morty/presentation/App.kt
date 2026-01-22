package org.carlosgub.yt.rick.and.morty.presentation

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.List
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import coil3.ImageLoader
import coil3.annotation.ExperimentalCoilApi
import coil3.compose.setSingletonImageLoaderFactory
import coil3.network.ktor3.KtorNetworkFetcherFactory
import org.carlosgub.yt.rick.and.morty.presentation.model.NavigationBarItemModel
import org.carlosgub.yt.rick.and.morty.presentation.navigation.Screen
import org.carlosgub.yt.rick.and.morty.presentation.screen.CharacterScreen
import org.carlosgub.yt.rick.and.morty.presentation.screen.EpisodeScreen
import org.carlosgub.yt.rick.and.morty.presentation.screen.LocationScreen

@OptIn(ExperimentalCoilApi::class)
@Composable
@Preview
fun App() {
    MaterialTheme {
        setSingletonImageLoaderFactory { context ->
            ImageLoader.Builder(context)
                .components {
                    add(KtorNetworkFetcherFactory())
                }
                .build()
        }

        val navController = rememberNavController()

        val items = listOf(
            NavigationBarItemModel(
                label = "Characters",
                icon = Icons.Filled.Person,
                route = Screen.Characters
            ),
            NavigationBarItemModel(
                label = "Locations",
                icon = Icons.Filled.LocationOn,
                route = Screen.Locations
            ),
            NavigationBarItemModel(
                label = "Episodes",
                icon = Icons.AutoMirrored.Filled.List,
                route = Screen.Episodes
            )
        )

        var selectedItem by remember { mutableStateOf(items.first().route) }

        Scaffold(
            modifier = Modifier.fillMaxSize(),
            containerColor = MaterialTheme.colorScheme.background,
            bottomBar = {
                NavigationBar {
                    items.forEach { (label, icon, route) ->
                        NavigationBarItem(
                            icon = { Icon(icon, contentDescription = label) },
                            label = { Text(label) },
                            selected = selectedItem == route,
                            onClick = {
                                selectedItem = route
                                navController.navigate(route) {
                                    popUpTo(navController.graph.findStartDestination().id) {
                                        saveState = true
                                    }
                                    launchSingleTop = true
                                    restoreState = true
                                }
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
                    CharacterScreen()
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
}
