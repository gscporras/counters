package com.counter.android.ui.custom

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.counter.android.ui.theme.gray80

@Composable
@Preview
fun CounterEmpty(
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .padding(50.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "No counters yet")
        Text(
            modifier = Modifier
                .padding(top = 8.dp),
            textAlign = TextAlign.Center,
            text = "“When I started counting my blessings, my whole life turned around.”\n" +
                    "—Willie Nelson",
            color = gray80
        )
    }
}