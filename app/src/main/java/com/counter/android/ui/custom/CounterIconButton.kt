package com.counter.android.ui.custom

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Add
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.counter.android.ui.theme.purple200
import com.counter.android.ui.theme.white

@Composable
fun CounterIconButton(
    modifier: Modifier = Modifier,
    onClick: () -> Unit = {}
) {
    Button(
        modifier = modifier,
        onClick = { onClick() },
        colors = ButtonDefaults.buttonColors(
            backgroundColor = purple200
        ),
        shape = RoundedCornerShape(32.dp)
    ) {
        Row {
            Icon(
                imageVector = Icons.Rounded.Add,
                contentDescription = null,
                tint = white
            )
            Text(
                modifier = Modifier
                    .padding(start = 8.dp),
                text = "Add Counter",
                color = white
            )
        }
    }
}