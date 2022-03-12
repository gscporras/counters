package com.counter.android.ui.custom

import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.counter.android.ui.theme.*

@Composable
@Preview
fun CounterButton(
    modifier: Modifier = Modifier,
    text: String = "Text button",
    onClick: () -> Unit = {}
) {
    Button(
        modifier = modifier
            .height(48.dp),
        onClick = { onClick() },
        colors = ButtonDefaults.buttonColors(
            backgroundColor = purple200
        ),
        shape = RoundedCornerShape(2.dp)
    ) {
        Text(
            text = text,
            style = MaterialTheme.typography.button,
            color = white
        )
    }
}