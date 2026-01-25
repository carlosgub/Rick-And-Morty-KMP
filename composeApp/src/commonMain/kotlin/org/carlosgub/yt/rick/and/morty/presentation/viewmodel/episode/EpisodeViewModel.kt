package org.carlosgub.yt.rick.and.morty.presentation.viewmodel.episode

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import org.carlosgub.yt.rick.and.morty.domain.repository.EpisodeRepository
import org.orbitmvi.orbit.ContainerHost
import org.orbitmvi.orbit.container

class EpisodeViewModel(
    private val episodeRepository: EpisodeRepository
) : ViewModel(), ContainerHost<EpisodeState, EpisodeSideEffect> {

    override val container = viewModelScope.container<EpisodeState, EpisodeSideEffect>(
        EpisodeState()
    )

    init {
        getEpisodes()
    }

    private fun getEpisodes() = intent {
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