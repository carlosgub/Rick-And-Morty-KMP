package org.carlosgub.yt.rick.and.morty.presentation

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.Movie
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
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

import org.carlosgub.yt.rick.and.morty.di.initKoin
import org.koin.compose.KoinApplication
import org.koin.compose.KoinContext

@OptIn(ExperimentalCoilApi::class)
@Composable
@Preview
fun App() {
    KoinApplication(application = {
        // initKoin is now called in platform-specific entry points
    }) {
        MaterialTheme {

        setSingletonImageLoaderFactory { context ->
            ImageLoader.Builder(context)
                .components {
                    add(KtorNetworkFetcherFactory())
                }
                .build()
        }

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

        val navController = rememberNavController()
        var selectedItem by remember { mutableStateOf(items.first().route) }

        Scaffold(
            modifier = Modifier.fillMaxSize(),
            containerColor = Color.White,
            bottomBar = {
                NavigationBar {
                    items.forEach { item ->
                        NavigationBarItem(
                            selected = item.route == selectedItem,
                            label = { Text(item.label) },
                            onClick = {
                                selectedItem = item.route
                                navController.navigate(item.route)
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
}
