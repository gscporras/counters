package com.counter.android.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.runtime.CompositionLocalProvider
import com.counter.android.ui.home.HomeViewModel
import com.counter.android.ui.main.MainScreen
import com.counter.android.ui.main.MainViewModel
import com.counter.android.ui.theme.CounterTheme
import com.skydoves.landscapist.coil.LocalCoilImageLoader
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity: ComponentActivity() {

    private val mainViewModel: MainViewModel by viewModels()
    private val homeViewModel: HomeViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CompositionLocalProvider(
                LocalCoilImageLoader provides mainViewModel.imageLoader
            ) {

                CounterTheme() {

                    MainScreen()
                }
            }
        }
    }
}