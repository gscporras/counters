package com.counter.android.persistence

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.counter.android.models.entities.Counter

@Dao
interface CounterDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCounters(counters: List<Counter>)

    @Query("DELETE FROM Counter")
    suspend fun deleteCounter()

    @Query("SELECT * FROM Counter")
    suspend fun getCounters(): List<Counter>
}