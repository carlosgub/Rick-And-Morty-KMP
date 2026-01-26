package org.carlosgub.yt.rick.and.morty.presentation.utils

import androidx.compose.ui.graphics.Color
import org.carlosgub.yt.rick.and.morty.domain.model.Character

fun Character.statusColor():Color = when (this.status.lowercase()) {
    "alive" -> Color(0XFF4CAF50)
    "dead" -> Color.Red
    else -> Color.Gray
}