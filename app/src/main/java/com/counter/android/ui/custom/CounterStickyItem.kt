package com.counter.android.ui.custom

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.counter.android.R
import com.counter.android.models.entities.Counter

@Composable
@Preview
fun CounterStickyItem(
    counters: List<Counter> = listOf()
) {
    val context = LocalContext.current

    Row(
        modifier = Modifier
            .padding(
                start = 16.dp,
                top = 8.dp
            ),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = context.resources.getQuantityString(R.plurals.quantity_items, counters.size, counters.size),
            style = MaterialTheme.typography.h5
        )
        val times = counters.sumOf { it.count }
        Text(
            modifier = Modifier
                .padding(start = 8.dp),
            text = context.resources.getQuantityString(R.plurals.times_items, times, times),
            style = MaterialTheme.typography.body1
        )
    }
}