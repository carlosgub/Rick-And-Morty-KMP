package org.carlosgub.yt.rick.and.morty.di

import io.ktor.client.HttpClient
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.defaultRequest
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json
import org.carlosgub.yt.rick.and.morty.data.remote.RickAndMortyApi
import org.koin.dsl.module

val networkModule = module {
    single {
        HttpClient {
            install(ContentNegotiation) {
                json(
                    Json {
                        ignoreUnknownKeys = true
                        prettyPrint = true
                    }
                )
            }
            defaultRequest {
                url("https://rickandmortyapi.com")
            }
        }
    }
    single { RickAndMortyApi(get()) }
}