package org.carlosgub.yt.rick.and.morty.presentation.navigation

import androidx.compose.runtime.compositionLocalOf
import androidx.navigation.NavController

val LocalNavController = compositionLocalOf<NavController> { error("No NavController found!") }
