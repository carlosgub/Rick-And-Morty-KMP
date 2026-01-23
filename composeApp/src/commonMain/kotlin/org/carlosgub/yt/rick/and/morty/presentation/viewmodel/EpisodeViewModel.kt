package org.carlosgub.yt.rick.and.morty.presentation.viewmodel

import androidx.compose.runtime.remember
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import org.carlosgub.yt.rick.and.morty.domain.model.Episode
import org.carlosgub.yt.rick.and.morty.domain.repository.EpisodeRepository
import org.orbitmvi.orbit.ContainerHost
import org.orbitmvi.orbit.container
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.reduce

data class EpisodeState(
    val episodes: List<Episode> = emptyList(),
    val isLoading: Boolean = false,
    val error: String? = null
) {
    val episodesBySeason = episodes.groupBy { it.episode.substring(0, 3) }
}

sealed class EpisodeSideEffect

class EpisodeViewModel(
    private val episodeRepository: EpisodeRepository
) : ViewModel(), ContainerHost<EpisodeState, EpisodeSideEffect> {

    override val container =
        viewModelScope.container<EpisodeState, EpisodeSideEffect>(EpisodeState())

    init {
        getEpisodes()
    }

    private fun getEpisodes() = intent {
        if (state.episodes.isNotEmpty()) return@intent
        reduce { state.copy(isLoading = true) }
        val episodes = episodeRepository.getEpisodes(1)
        reduce {
            state.copy(
                isLoading = false,
                episodes = episodes
            )
        }
    }
}
