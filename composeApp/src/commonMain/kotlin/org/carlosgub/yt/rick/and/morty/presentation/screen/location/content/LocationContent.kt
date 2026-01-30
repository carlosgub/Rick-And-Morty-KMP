package org.carlosgub.yt.rick.and.morty.presentation.screen.location.content

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.unit.dp
import org.carlosgub.yt.rick.and.morty.presentation.screen.location.preview.LocationStateParameterProvider
import org.carlosgub.yt.rick.and.morty.presentation.viewmodel.location.LocationState

@Composable
internal fun LocationContent(state: LocationState) {
    Box(
        modifier = Modifier.fillMaxSize()
            .background(Color.White),
    ) {
        if (state.isLoading) {
            CircularProgressIndicator(
                modifier = Modifier.align(Alignment.Center)
            )
        }
        if (state.locations.isNotEmpty()) {
            LazyColumn(
                modifier = Modifier.fillMaxSize(),
                contentPadding = PaddingValues(16.dp),
                verticalArrangement = Arrangement.spacedBy(6.dp)
            ) {
                items(
                    items = state.locations,
                    key = { it.id },
                ) { location ->
                    LocationItem(location = location)
                }
            }
        }
        state.errorMessage?.let { message ->
            Text(
                message,
                modifier = Modifier.align(Alignment.Center)
                    .padding(16.dp)
            )
        }
    }
}

@Preview
@Composable
private fun LocationContentPreview(
    @PreviewParameter(LocationStateParameterProvider::class) state: LocationState,
) {
    LocationContent(
        state = state
    )
}