package org.carlosgub.yt.rick.and.morty.presentation

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.mutableStateListOf
import coil3.ImageLoader
import coil3.annotation.ExperimentalCoilApi
import coil3.compose.setSingletonImageLoaderFactory
import coil3.network.ktor3.KtorNetworkFetcherFactory
import org.carlosgub.yt.rick.and.morty.presentation.navigation.LocalNavController
import org.carlosgub.yt.rick.and.morty.presentation.navigation.Screen
import org.carlosgub.yt.rick.and.morty.presentation.screen.characterdetail.CharacterDetailScreen
import org.carlosgub.yt.rick.and.morty.presentation.screen.home.HomeScreen
import androidx.compose.runtime.remember
import androidx.navigation3.runtime.NavEntry
import androidx.navigation3.ui.NavDisplay

@OptIn(ExperimentalCoilApi::class)
@Composable
fun App() {
    MaterialTheme {
        setSingletonImageLoaderFactory { context ->
            ImageLoader
                .Builder(context)
                .components {
                    add(KtorNetworkFetcherFactory())
                }.build()
        }

        val backStack = remember { mutableStateListOf<Any>(Screen.Home) }
        CompositionLocalProvider(LocalNavController provides backStack) {
            NavDisplay(
                backStack = backStack,
            ) { key ->
                NavEntry(key) {
                    when (key) {
                        is Screen.Home -> {
                            HomeScreen()
                        }

                        is Screen.CharacterDetail -> {
                            CharacterDetailScreen(characterId = key.id)
                        }
                    }
                }
            }
        }
    }
}
