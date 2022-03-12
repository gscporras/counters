package com.counter.android.persistence

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.counter.android.models.entities.Counter
import com.counter.android.persistence.converters.IntegerListConverter
import com.counter.android.persistence.converters.StringListConverter

@Database(
    entities = [(Counter::class)],
    version = 3, exportSchema = false
)
@TypeConverters(
    value = [
        (StringListConverter::class),
        (IntegerListConverter::class)
    ]
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun countDao(): CounterDao
}