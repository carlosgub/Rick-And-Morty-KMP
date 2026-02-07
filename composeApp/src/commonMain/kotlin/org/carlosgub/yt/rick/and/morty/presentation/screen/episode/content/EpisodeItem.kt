package org.carlosgub.yt.rick.and.morty.presentation.screen.episode.content

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import org.carlosgub.yt.rick.and.morty.domain.model.Episode

@Composable
fun EpisodeItem(episode: Episode) {
    Card(
        modifier =
            Modifier
                .fillMaxSize()
                .padding(4.dp),
        shape = RoundedCornerShape(16.dp),
        colors =
            CardDefaults.cardColors(
                containerColor = Color.White,
            ),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
    ) {
        Column(
            modifier =
                Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
        ) {
            EpisodeName(episode.name)
            EpisodeSeasonDetail(episode.episode)
            EpisodeAirDate(episode.airDate)
        }
    }
}

@Composable
private fun EpisodeName(name: String) {
    Text(
        text = name,
        color = Color.Black,
        fontSize = 18.sp,
        fontWeight = FontWeight.SemiBold,
    )
}

@Composable
private fun EpisodeSeasonDetail(episode: String) {
    Row(
        modifier =
            Modifier
                .fillMaxWidth()
                .padding(top = 6.dp),
    ) {
        Text(
            text = "Episode:",
            color = MaterialTheme.colorScheme.primary,
            fontSize = 12.sp,
            fontWeight = FontWeight.Normal,
        )
        Text(
            text = episode,
            color = Color.DarkGray,
            fontSize = 14.sp,
            fontWeight = FontWeight.Normal,
            modifier = Modifier.padding(start = 4.dp),
        )
    }
}

@Composable
private fun EpisodeAirDate(airDate: String) {
    Row(
        modifier =
            Modifier
                .fillMaxWidth()
                .padding(top = 4.dp),
    ) {
        Text(
            text = "Air date:",
            color = MaterialTheme.colorScheme.primary,
            fontSize = 12.sp,
            fontWeight = FontWeight.Normal,
        )
        Text(
            text = airDate,
            color = Color.DarkGray,
            fontSize = 14.sp,
            fontWeight = FontWeight.Normal,
            modifier = Modifier.padding(start = 4.dp),
        )
    }
}
