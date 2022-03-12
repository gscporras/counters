package com.counter.android.network.service

import com.counter.android.models.entities.CounterAdd
import com.counter.android.models.entities.Counter
import com.counter.android.models.entities.CounterId
import com.skydoves.sandwich.ApiResponse
import retrofit2.http.*

interface CounterService {

    @GET("/api/v1/counters")
    suspend fun getCounters(): ApiResponse<List<Counter>>

    @POST("/api/v1/counter")
    suspend fun createCounter(
        @Body body: CounterAdd
    ): ApiResponse<List<Counter>>

    @POST("/api/v1/counter/inc")
    suspend fun counterInc(
        @Body body: CounterId
    ): ApiResponse<List<Counter>>

    @POST("/api/v1/counter/dec")
    suspend fun counterDec(
        @Body body: CounterId
    ): ApiResponse<List<Counter>>

    @HTTP(method = "DELETE", path = "/api/v1/counter", hasBody = true)
    suspend fun deleteCounter(
        @Body body: CounterId
    ): ApiResponse<List<Counter>>
}