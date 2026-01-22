package org.carlosgub.yt.rick.and.morty.presentation.model

import androidx.compose.ui.graphics.vector.ImageVector
import org.carlosgub.yt.rick.and.morty.presentation.navigation.Screen

data class NavigationBarItemModel(
    val label: String,
    val icon: ImageVector,
    val route: Screen,
)
