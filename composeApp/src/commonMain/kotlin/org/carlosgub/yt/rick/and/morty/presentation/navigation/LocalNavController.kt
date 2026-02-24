package org.carlosgub.yt.rick.and.morty.presentation.navigation

import androidx.compose.runtime.compositionLocalOf

val LocalNavController = compositionLocalOf<MutableList<Any>> { error("No NavController found!") }
