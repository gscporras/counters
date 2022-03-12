package com.counter.android.ui.createcounter

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.counter.android.models.entities.Counter
import com.counter.android.repository.CounterRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CreateCounterViewModel @Inject constructor(
    private val repository: CounterRepository
): ViewModel() {

    private val _navigateToHome = MutableStateFlow(false)
    val navigateToHome: StateFlow<Boolean> get() = _navigateToHome

    fun save(name: String) {
        viewModelScope.launch {
            repository.addCounter(
                name = name,
                start = {},
                success = {},
                error = {}
            ).collect {
                _navigateToHome.value = true
            }
        }
    }
}