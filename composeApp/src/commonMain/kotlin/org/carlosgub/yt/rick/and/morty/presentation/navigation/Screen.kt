package org.carlosgub.yt.rick.and.morty.presentation.navigation

import kotlinx.serialization.Serializable

sealed class Screen {

    @Serializable
    object Home: Screen()

    @Serializable
    object Characters: Screen()

    @Serializable
    object Locations: Screen()

    @Serializable
    object Episodes: Screen()

    @Serializable
    data class CharacterDetail(val id: Int) : Screen()
}