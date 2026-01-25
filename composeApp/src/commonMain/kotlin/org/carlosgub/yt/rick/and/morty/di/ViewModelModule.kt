package org.carlosgub.yt.rick.and.morty.di

import org.carlosgub.yt.rick.and.morty.presentation.viewmodel.character.CharacterViewModel
import org.carlosgub.yt.rick.and.morty.presentation.viewmodel.character.detail.CharacterDetailViewModel
import org.carlosgub.yt.rick.and.morty.presentation.viewmodel.episode.EpisodeViewModel
import org.carlosgub.yt.rick.and.morty.presentation.viewmodel.location.LocationViewModel
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel { CharacterViewModel(get()) }
    viewModel { LocationViewModel(get()) }
    viewModel { EpisodeViewModel(get()) }
    viewModel { CharacterDetailViewModel(get(), get()) }
}