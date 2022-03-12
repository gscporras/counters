package com.counter.android.ui.splash

import androidx.lifecycle.ViewModel
import com.counter.android.repository.CounterRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class SplashViewModel @Inject constructor(
    private val counterRepository: CounterRepository
): ViewModel() {

    init {
        Timber.d("init SplashViewModel")
    }

    private val coroutineScope = MainScope()

    private val _oneShotEvents = Channel<OneShotEvent>(Channel.BUFFERED)
    val oneShotEvents = _oneShotEvents.receiveAsFlow()

    fun onNavigateHome(uiAction: UiAction) {
        when (uiAction) {
            is UiAction.NavigateToHome -> {
                coroutineScope.launch {
                    _oneShotEvents.send(OneShotEvent.NavigateToHome)
                }
            }
        }
    }

    sealed class OneShotEvent {
        object NavigateToHome: OneShotEvent()
    }

    sealed class UiAction {
        object NavigateToHome : UiAction()
    }
}