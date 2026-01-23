package org.carlosgub.yt.rick.and.morty.di

import org.carlosgub.yt.rick.and.morty.presentation.viewmodel.CharacterViewModel
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel { CharacterViewModel(get()) }
}