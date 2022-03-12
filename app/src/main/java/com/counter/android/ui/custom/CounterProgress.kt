package com.counter.android.ui.custom

import androidx.compose.material.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.counter.android.ui.theme.purple200

@Composable
@Preview
fun CounterProgress(
    modifier: Modifier = Modifier
) {
    CircularProgressIndicator(
        modifier = modifier,
        color = purple200
    )
}