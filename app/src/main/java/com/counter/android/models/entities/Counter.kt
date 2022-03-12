package com.counter.android.models.entities

import androidx.room.Entity
import javax.annotation.concurrent.Immutable

@Immutable
@Entity(primaryKeys = [("id")])
data class Counter(
    val id: String = "",
    val title: String = "",
    val count: Int = 0
)