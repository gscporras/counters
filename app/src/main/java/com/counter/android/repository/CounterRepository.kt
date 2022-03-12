package com.counter.android.repository

import androidx.annotation.WorkerThread
import com.counter.android.mapper.ErrorResponseMapper
import com.counter.android.models.entities.Counter
import com.counter.android.models.entities.CounterAdd
import com.counter.android.models.entities.CounterId
import com.counter.android.network.service.CounterService
import com.counter.android.persistence.CounterDao
import com.skydoves.sandwich.map
import com.skydoves.sandwich.onError
import com.skydoves.sandwich.onException
import com.skydoves.sandwich.suspendOnSuccess
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.onCompletion
import kotlinx.coroutines.flow.onStart
import timber.log.Timber
import javax.inject.Inject

class CounterRepository @Inject constructor(
    private val counterService: CounterService,
    private val counterDao: CounterDao
): Repository {

    init {
        Timber.d("Injection CounterRepository")
    }

    @WorkerThread
    fun getCounters(
        start: () -> Unit,
        success: () -> Unit,
        error: (String?) -> Unit
    ) = flow {
        //val counters = counterDao.getCounters()
        val response = counterService.getCounters()
        response.suspendOnSuccess {
            //counterDao.insertCounters(data)
            emit(data)
        }.onError {
            map(ErrorResponseMapper) { error("[Code: $code]: $message") }
        }.onException {
            error(message)
        }
    }.onStart { start() }.onCompletion { success() }.flowOn(Dispatchers.IO)

    @WorkerThread
    fun addCounter(
        name: String,
        start: () -> Unit,
        success: () -> Unit,
        error: (String?) -> Unit
    ) = flow {
        val response = counterService.createCounter(
            CounterAdd(title = name)
        )
        response.suspendOnSuccess {
            emit(data)
        }.onError {
            map(ErrorResponseMapper) { error("[Code: $code]: $message") }
        }.onException {
            error(message)
        }
    }.onStart { start() }.onCompletion { success() }.flowOn(Dispatchers.IO)

    @WorkerThread
    fun increment(
        counter: Counter,
        start: () -> Unit,
        success: () -> Unit,
        error: (String?) -> Unit
    ) = flow {
        val response = counterService.counterInc(
            CounterId(counter.id)
        )
        response.suspendOnSuccess {
            emit(data)
        }.onError {
            map(ErrorResponseMapper) { error("[Code: $code]: $message") }
        }.onException {
            error(message)
        }
    }.onStart { start() }.onCompletion { success() }.flowOn(Dispatchers.IO)

    @WorkerThread
    fun decrement(
        counter: Counter,
        start: () -> Unit,
        success: () -> Unit,
        error: (String?) -> Unit
    ) = flow {
        val response = counterService.counterDec(
            CounterId(counter.id)
        )
        response.suspendOnSuccess {
            emit(data)
        }.onError {
            map(ErrorResponseMapper) { error("[Code: $code]: $message") }
        }.onException {
            error(message)
        }
    }.onStart { start() }.onCompletion { success() }.flowOn(Dispatchers.IO)

    @WorkerThread
    fun deleteCounter(
        counter: Counter,
        start: () -> Unit,
        success: () -> Unit,
        error: (String?) -> Unit
    ) = flow {
        val response = counterService.deleteCounter(
            CounterId(counter.id)
        )
        response.suspendOnSuccess {
            emit(data)
        }.onError {
            map(ErrorResponseMapper) { error("[Code: $code]: $message") }
        }.onException {
            error(message)
        }
    }.onStart { start() }.onCompletion { success() }.flowOn(Dispatchers.IO)
}