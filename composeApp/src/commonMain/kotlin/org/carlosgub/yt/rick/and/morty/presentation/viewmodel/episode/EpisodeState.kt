package org.carlosgub.yt.rick.and.morty.presentation.viewmodel.episode

import org.carlosgub.yt.rick.and.morty.domain.model.Episode

data class EpisodeState(
    val isLoading: Boolean = false,
    val episodes: List<Episode> = emptyList(),
    val errorMessage:String? = null
){
    val episodesPerSeason = episodes.groupBy { it.episode.substring(0, 3) }
}