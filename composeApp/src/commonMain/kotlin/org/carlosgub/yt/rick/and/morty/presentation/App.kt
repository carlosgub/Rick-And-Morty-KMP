package org.carlosgub.yt.rick.and.morty.presentation

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import coil3.ImageLoader
import coil3.annotation.ExperimentalCoilApi
import coil3.compose.setSingletonImageLoaderFactory
import coil3.network.ktor3.KtorNetworkFetcherFactory
import org.carlosgub.yt.rick.and.morty.presentation.navigation.LocalNavController
import org.carlosgub.yt.rick.and.morty.presentation.navigation.Screen
import org.carlosgub.yt.rick.and.morty.presentation.screen.characterdetail.CharacterDetailScreen
import org.carlosgub.yt.rick.and.morty.presentation.screen.home.HomeScreen

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
        CompositionLocalProvider(LocalNavController provides navController) {
            NavHost(
                navController = navController,
                startDestination = Screen.Home
            ) {
                composable<Screen.Home> {
                    HomeScreen()
                }
                composable<Screen.CharacterDetail> { backStackEntry ->
                    val characterDetail: Screen.CharacterDetail = backStackEntry.toRoute()
                    CharacterDetailScreen(characterDetail.id)
                }
            }
        }
    }
}
