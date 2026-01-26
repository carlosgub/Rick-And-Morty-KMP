package org.carlosgub.yt.rick.and.morty.presentation.screen.characterdetail

import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import org.carlosgub.yt.rick.and.morty.presentation.screen.characterdetail.content.CharacterDetailContent
import org.carlosgub.yt.rick.and.morty.presentation.screen.characterdetail.observer.CharacterDetailObserver
import org.carlosgub.yt.rick.and.morty.presentation.viewmodel.characterdetail.CharacterDetailViewModel
import org.koin.compose.viewmodel.koinViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CharacterDetailScreen() {
    val viewModel = koinViewModel<CharacterDetailViewModel>()
    val state = viewModel.container.stateFlow.collectAsStateWithLifecycle().value

    CharacterDetailObserver(viewModel)

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        state.character?.name.orEmpty(),
                        fontWeight = FontWeight.Bold
                    )
                },
                navigationIcon = {
                    IconButton(onClick = viewModel::navigateBack) {
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
    ) { paddingValues ->
        CharacterDetailContent(
            state = state,
            modifier = Modifier.padding(paddingValues)
        )
    }

}