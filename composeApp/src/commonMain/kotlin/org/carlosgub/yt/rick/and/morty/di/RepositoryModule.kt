package org.carlosgub.yt.rick.and.morty.di

import org.carlosgub.yt.rick.and.morty.data.repository.CharacterRepositoryImpl
import org.carlosgub.yt.rick.and.morty.data.repository.EpisodeRepositoryImpl
import org.carlosgub.yt.rick.and.morty.data.repository.LocationRepositoryImpl
import org.carlosgub.yt.rick.and.morty.domain.repository.CharacterRepository
import org.carlosgub.yt.rick.and.morty.domain.repository.EpisodeRepository
import org.carlosgub.yt.rick.and.morty.domain.repository.LocationRepository
import org.koin.dsl.module

val repositoryModule =
    module {
        single<CharacterRepository> {
            CharacterRepositoryImpl(get())
        }
        single<LocationRepository> {
            LocationRepositoryImpl(get())
        }
        single<EpisodeRepository> {
            EpisodeRepositoryImpl(get())
        }
    }
