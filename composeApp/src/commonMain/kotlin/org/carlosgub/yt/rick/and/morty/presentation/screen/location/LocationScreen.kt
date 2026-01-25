package org.carlosgub.yt.rick.and.morty.presentation.screen.location

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import org.carlosgub.yt.rick.and.morty.presentation.screen.location.content.LocationContent
import org.carlosgub.yt.rick.and.morty.presentation.viewmodel.location.LocationViewModel
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun LocationScreen() {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        val viewModel = koinViewModel<LocationViewModel>()
        val state = viewModel.container.stateFlow.collectAsStateWithLifecycle().value

        LocationContent(state)
    }
}