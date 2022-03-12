package com.counter.android.ui.custom

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import com.counter.android.ui.theme.gray80

@Composable
@Preview
fun TopSearchCard(
    state: MutableState<String> = mutableStateOf(""),
    onBack: () -> Unit = {},
    onSearch: () -> Unit = {}
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(64.dp),
        onClick = {  },
        shape = RoundedCornerShape(0.dp),
        elevation = 20.dp,
        backgroundColor = MaterialTheme.colors.background
    ) {
        ConstraintLayout {
            val (icon, input) = createRefs()

            IconButton(
                modifier = Modifier
                    .constrainAs(icon) {
                        top.linkTo(parent.top)
                        bottom.linkTo(parent.bottom)
                        start.linkTo(parent.start, 6.dp)
                    },
                onClick = { onBack() }
            ) {
                Icon(
                    imageVector = Icons.Rounded.ArrowBack,
                    contentDescription = null,
                    tint = MaterialTheme.colors.primary
                )
            }
            TextField(
                modifier = Modifier
                    .constrainAs(input) {
                        top.linkTo(parent.top)
                        bottom.linkTo(parent.bottom)
                        start.linkTo(icon.end, 16.dp)
                    },
                value = state.value,
                onValueChange = {
                    state.value = it
                    onSearch()
                },
                colors = TextFieldDefaults.textFieldColors(
                    backgroundColor = Color.Transparent
                ),
                placeholder = {
                    Text(
                        text = "Search Counters",
                        style = MaterialTheme.typography.body1,
                        color = gray80
                    )
                },
                maxLines = 1,
                singleLine = true
            )
        }
    }
}