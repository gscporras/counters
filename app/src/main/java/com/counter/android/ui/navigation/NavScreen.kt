package com.counter.android.ui.navigation

import androidx.compose.runtime.Immutable

@Immutable
sealed class NavScreen(val route: String) {

    object Splash : NavScreen("Splash")

    object Home : NavScreen("Home")

    object CreateCounter : NavScreen("CreateCounter")
}