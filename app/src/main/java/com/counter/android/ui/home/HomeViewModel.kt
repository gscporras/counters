package com.counter.android.ui.home

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
class HomeViewModel @Inject constructor(
    private val counterRepository: CounterRepository
): ViewModel() {

    private val _counters = MutableStateFlow<List<Counter>>(emptyList())
    val counters: StateFlow<List<Counter>> get() = _counters

    private val _filteredCounters = MutableStateFlow<List<Counter>>(emptyList())
    val filteredCounters: StateFlow<List<Counter>> get() = _filteredCounters

    private val _isRefreshing = MutableStateFlow(false)
    val isRefreshing: StateFlow<Boolean> get() = _isRefreshing

    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> get() = _isLoading

    private val _isLoadingFiltered = MutableStateFlow(false)
    val isLoadingFiltered: StateFlow<Boolean> get() = _isLoadingFiltered

    fun getCounters() {
        viewModelScope.launch {
            counterRepository.getCounters(
                start = { _isRefreshing.value = true },
                success = { _isRefreshing.value = false },
                error = { _isRefreshing.value = false }
            ).collect {
                _counters.value = it
            }
        }
    }

    fun refresh() {
        getCounters()
    }

    fun filterCounters(text: String) {
        if(text.isEmpty()) {
            _filteredCounters.value = emptyList()
        } else {
            val filterList = mutableListOf<Counter>()
            for(item in counters.value) {
                if(item.title.lowercase().contains(text.lowercase())) {
                    filterList.add(item)
                }
            }
            _filteredCounters.value = filterList
        }
    }

    fun decrement(counter: Counter) {
        if(counter.count == 1) {
            deleteCounter(counter = counter)
        } else {
            viewModelScope.launch {
                counterRepository.decrement(
                    counter = counter,
                    start = { _isLoading.value = true },
                    success = { _isLoading.value = false },
                    error = { _isLoading.value = false }
                ).collect {
                    _counters.value = it
                    _filteredCounters.value = it
                }
            }
        }
    }

    fun decrementFiltered(counter: Counter) {
        if(counter.count == 1) {
            deleteCounterFiltered(counter = counter)
        } else {
            viewModelScope.launch {
                counterRepository.decrement(
                    counter = counter,
                    start = { _isLoadingFiltered.value = true },
                    success = { _isLoadingFiltered.value = false },
                    error = { _isLoadingFiltered.value = false }
                ).collect { response ->
                    _filteredCounters.value = filteredCounters.value.flatMap { counter ->
                        response.filter {
                            it.id == counter.id
                        }
                    }
                }
            }
        }
    }

    fun increment(counter: Counter) {
        viewModelScope.launch {
            counterRepository.increment(
                counter = counter,
                start = { _isLoading.value = true },
                success = { _isLoading.value = false },
                error = { _isLoading.value = false }
            ).collect {
                _counters.value = it
                _filteredCounters.value = it
            }
        }
    }

    fun incrementFiltered(counter: Counter) {
        viewModelScope.launch {
            counterRepository.increment(
                counter = counter,
                start = { _isLoadingFiltered.value = true },
                success = { _isLoadingFiltered.value = false },
                error = { _isLoadingFiltered.value = false }
            ).collect { response ->
                _filteredCounters.value = filteredCounters.value.flatMap { counter ->
                    response.filter {
                        it.id == counter.id
                    }
                }
            }
        }
    }

    fun deleteCounter(counter: Counter) {
        viewModelScope.launch {
            counterRepository.deleteCounter(
                counter = counter,
                start = { _isLoading.value = true },
                success = { _isLoading.value = false },
                error = { _isLoading.value = false }
            ).collect {
                _counters.value = it
            }
        }
    }

    fun deleteCounterFiltered(counter: Counter) {
        viewModelScope.launch {
            counterRepository.deleteCounter(
                counter = counter,
                start = { _isLoading.value = true },
                success = { _isLoading.value = false },
                error = { _isLoading.value = false }
            ).collect { response ->
                _filteredCounters.value = filteredCounters.value.flatMap { counter ->
                    response.filter {
                        it.id == counter.id
                    }
                }
            }
        }
    }
}