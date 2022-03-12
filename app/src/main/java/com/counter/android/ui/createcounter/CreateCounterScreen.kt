package com.counter.android.ui.createcounter

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Close
import androidx.compose.material.icons.rounded.Save
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import com.counter.android.extensions.showToast
import com.counter.android.ui.theme.gray80
import com.counter.android.ui.theme.white

@Composable
fun CreateCounterScreen(
    viewModel: CreateCounterViewModel,
    pressOnBack: () -> Unit
) {
    val context = LocalContext.current
    val state = remember { mutableStateOf("") }
    val navigateToHome = viewModel.navigateToHome.collectAsState()

    if(navigateToHome.value) pressOnBack()

    Scaffold(
        modifier = Modifier
            .fillMaxSize(),
        topBar = {
            TopAppBar(
                title = {
                    Text(text = "Create Counter", color = white)
                },
                navigationIcon = {
                    IconButton(onClick = { pressOnBack() }) {
                        Icon(
                            imageVector = Icons.Rounded.Close,
                            contentDescription = null,
                            tint = white
                        )
                    }
                },
                actions = {
                    IconButton(
                        onClick = {
                            if(state.value.isNotEmpty()) {
                                viewModel.save(state.value)
                            } else {
                                context.showToast("Ingrese un nombre al contador")
                            }
                        }
                    ) {
                        Icon(
                            imageVector = Icons.Rounded.Save,
                            contentDescription = null,
                            tint = white
                        )
                    }
                }
            )
        },
        backgroundColor = MaterialTheme.colors.background
    ) {
        ConstraintLayout(
            modifier = Modifier
                .fillMaxSize()
        ) {

            val (input) = createRefs()

            TextField(
                modifier = Modifier
                    .constrainAs(input) {
                        top.linkTo(parent.top, 32.dp)
                        start.linkTo(parent.start, 16.dp)
                        end.linkTo(parent.end, 16.dp)
                        width = Dimension.fillToConstraints
                    },
                label = {
                    Text(text = "Name", color = white)
                },
                placeholder = {
                    Text(text = "Cups of coffee", color = gray80)
                },
                value = state.value,
                onValueChange = { state.value = it }
            )
        }
    }
}