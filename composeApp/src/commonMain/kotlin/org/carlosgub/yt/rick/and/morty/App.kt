package org.carlosgub.yt.rick.and.morty

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.safeContentPadding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil3.ImageLoader
import coil3.annotation.ExperimentalCoilApi
import coil3.compose.AsyncImage
import coil3.compose.setSingletonImageLoaderFactory
import coil3.network.ktor3.KtorNetworkFetcherFactory
import org.jetbrains.compose.resources.painterResource

import rickandmortykmp.composeapp.generated.resources.Res
import rickandmortykmp.composeapp.generated.resources.compose_multiplatform

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

        var showContent by remember { mutableStateOf(false) }
        Column(
            modifier = Modifier
                .background(MaterialTheme.colorScheme.primaryContainer)
                .safeContentPadding()
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Button(onClick = { showContent = !showContent }) {
                Text("Hola Mundo")
            }
            AsyncImage(
                model = "https://rickandmortyapi.com/api/character/avatar/337.jpeg",
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .height(300.dp)
            )
            AnimatedVisibility(showContent) {
                val greeting = remember { Greeting().greet() }
                Column(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalAlignment = Alignment.CenterHorizontally,
                ) {
                    Image(painterResource(Res.drawable.compose_multiplatform), null)
                    Text("Compose: $greeting")
                }
            }
        }
    }
}
