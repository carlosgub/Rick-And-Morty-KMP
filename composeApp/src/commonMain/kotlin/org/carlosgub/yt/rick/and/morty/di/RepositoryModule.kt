package org.carlosgub.yt.rick.and.morty.di

import org.carlosgub.yt.rick.and.morty.data.repository.CharacterRepositoryImpl
import org.carlosgub.yt.rick.and.morty.domain.repository.CharacterRepository
import org.koin.dsl.module

val repositoryModule = module {
    single<CharacterRepository> { CharacterRepositoryImpl(get()) }
}
