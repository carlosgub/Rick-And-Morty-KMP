package org.carlosgub.yt.rick.and.morty

import androidx.compose.ui.window.ComposeUIViewController
import org.carlosgub.yt.rick.and.morty.di.initKoin
import org.carlosgub.yt.rick.and.morty.presentation.App

fun MainViewController() =
    ComposeUIViewController(
        configure = {
            initKoin {}
        },
    ) { App() }
