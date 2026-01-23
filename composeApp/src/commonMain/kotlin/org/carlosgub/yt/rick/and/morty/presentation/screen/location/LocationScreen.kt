package org.carlosgub.yt.rick.and.morty.presentation.screen.location

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import org.carlosgub.yt.rick.and.morty.presentation.screen.location.content.LocationItem
import org.carlosgub.yt.rick.and.morty.presentation.screen.location.preview.LocationStateParameterProvider
import org.carlosgub.yt.rick.and.morty.presentation.viewmodel.LocationState
import org.carlosgub.yt.rick.and.morty.presentation.viewmodel.LocationViewModel
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun LocationScreen() {
    val viewModel = koinViewModel<LocationViewModel>()
    val state by viewModel.container.stateFlow.collectAsStateWithLifecycle()
    LocationContent(state = state)
}

@Composable
fun LocationContent(
    state: LocationState
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White),
        contentAlignment = Alignment.Center
    ) {
        if (state.isLoading) {
            CircularProgressIndicator(
                color = MaterialTheme.colorScheme.primary
            )
        } else if (state.error != null) {
            Text(
                text = "Error: ${state.error}",
                color = MaterialTheme.colorScheme.error,
                style = MaterialTheme.typography.bodyLarge
            )
        } else {
            LazyColumn(
                modifier = Modifier.fillMaxSize(),
                contentPadding = PaddingValues(16.dp),
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                item {
                    Text(
                        text = "Locations",
                        style = MaterialTheme.typography.headlineMedium,
                        modifier = Modifier.padding(bottom = 16.dp)
                    )
                }
                items(
                    items = state.locations,
                    key = { it.id }
                ) { location ->
                    LocationItem(location = location)
                }
            }
        }
    }
}

@Preview
@Composable
fun LocationScreenPreview(
    @PreviewParameter(LocationStateParameterProvider::class) state: LocationState
) {
    MaterialTheme {
        LocationContent(state = state)
    }
}
