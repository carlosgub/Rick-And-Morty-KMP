package org.carlosgub.yt.rick.and.morty.presentation.screen.characterdetail.content

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Transgender
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil3.compose.AsyncImage
import org.carlosgub.yt.rick.and.morty.domain.model.Character
import org.carlosgub.yt.rick.and.morty.presentation.screen.characterdetail.preview.CharacterDetailStateParameterProvider
import org.carlosgub.yt.rick.and.morty.presentation.utils.statusColor
import org.carlosgub.yt.rick.and.morty.presentation.viewmodel.characterdetail.CharacterDetailState

@Composable
fun CharacterDetailContent(
    state: CharacterDetailState,
    onNavigateBack: () -> Unit,
) {
    Scaffold(
        topBar = {
            CharacterDetailTopBar(
                title = state.character?.name.orEmpty(),
                onNavigateBack = onNavigateBack
            )
        },
    ) { paddingValues ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .background(Color.White),
        ) {
            if (state.isLoading) {
                CircularProgressIndicator(
                    modifier = Modifier.align(Alignment.Center)
                )
            }
            state.character?.let { character ->
                CharacterDetail(character = character)
            }
        }
    }

}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun CharacterDetailTopBar(
    title: String,
    onNavigateBack: () -> Unit
) {
    TopAppBar(
        title = {
            Text(
                title,
                fontWeight = FontWeight.Bold
            )
        },
        navigationIcon = {
            IconButton(onClick = onNavigateBack) {
                Icon(
                    imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                    contentDescription = "Back"
                )
            }
        },
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = Color.White
        )
    )
}

@Composable
private fun CharacterDetail(character: Character) {
    Column(
        modifier = Modifier.fillMaxSize()
            .verticalScroll(rememberScrollState())
    ) {
        Box(
            modifier = Modifier.fillMaxWidth()
                .height(350.dp)
        ) {
            AsyncImage(
                model = character.image,
                contentDescription = character.name,
                modifier = Modifier.fillMaxSize(),
                contentScale = ContentScale.Crop
            )
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .align(Alignment.BottomStart)
                    .background(
                        Brush.verticalGradient(
                            colors = listOf(
                                Color.Transparent,
                                Color.Black
                            ),
                        )
                    )
                    .padding(24.dp)

            ) {
                Text(
                    text = character.name,
                    color = Color.White,
                    fontSize = 32.sp,
                    fontWeight = FontWeight.Bold
                )

                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.fillMaxWidth()
                        .padding(top = 4.dp)
                ) {
                    Box(
                        modifier = Modifier
                            .size(14.dp)
                            .clip(CircleShape)
                            .background(character.statusColor())
                    )
                    Text(
                        text = "${character.status} - ${character.species}",
                        color = Color.White,
                        fontSize = 16.sp,
                        modifier = Modifier.padding(start = 6.dp)
                    )
                }
            }
        }
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(24.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            InfoCard(
                icon = Icons.Default.Transgender,
                title = "Gender",
                description = character.gender
            )
            InfoCard(
                icon = Icons.Default.Person,
                title = "Species",
                description = character.species
            )
            InfoCard(
                icon = Icons.Default.LocationOn,
                title = "Last Known Location",
                description = character.location
            )
        }
    }
}

@Composable
private fun InfoCard(
    icon: ImageVector,
    title: String,
    description: String,
) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color(0XFFF5F5F5)
        ),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth()
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Icon(
                imageVector = icon,
                contentDescription = null,
                tint = MaterialTheme.colorScheme.primary,
                modifier = Modifier.size(32.dp)
            )
            Column(
                modifier = Modifier.weight(1f)
                    .padding(start = 16.dp)
            ) {
                Text(
                    text = title,
                    color = Color.Gray,
                    fontSize = 14.sp
                )
                Text(
                    text = description,
                    color = Color.Black,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.SemiBold
                )
            }
        }
    }
}

@Preview
@Composable
private fun CharacterDetailContentPreview(
    @PreviewParameter(CharacterDetailStateParameterProvider::class) state: CharacterDetailState,
) {
    CharacterDetailContent(
        state = state,
        onNavigateBack = {}
    )
}