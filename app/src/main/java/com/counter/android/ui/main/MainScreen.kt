package com.counter.android.ui.main

import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.counter.android.ui.createcounter.CreateCounterScreen
import com.counter.android.ui.home.HomeScreen
import com.counter.android.ui.home.HomeTabStateHolder
import com.counter.android.ui.navigation.NavScreen
import com.counter.android.ui.splash.SplashScreen
import com.google.accompanist.insets.ProvideWindowInsets

@Composable
fun MainScreen() {
    val navController = rememberNavController()
    val tabStateHolder = HomeTabStateHolder(
        rememberLazyListState()
    )

    val context = LocalContext.current

    ProvideWindowInsets {
        NavHost(navController = navController, startDestination = NavScreen.Home.route) {
            composable(
                route = NavScreen.Splash.route,
                arguments = emptyList()
            ) {
                SplashScreen(
                    viewModel = hiltViewModel(),
                    navigateToHome = {
                        navController.navigate(NavScreen.Home.route)
                    }
                )
            }
            composable(
                route = NavScreen.Home.route,
                arguments = emptyList()
            ) {
                HomeScreen(
                    viewModel = hiltViewModel(),
                    navigateToCreateCounter = {
                        navController.navigate(NavScreen.CreateCounter.route)
                    }
                )
            }
            composable(
                route = NavScreen.CreateCounter.route,
                arguments = emptyList()
            ) {
                CreateCounterScreen(
                    viewModel = hiltViewModel(),
                    pressOnBack = {
                        navController.navigateUp()
                    }
                )
            }
        }
    }
}