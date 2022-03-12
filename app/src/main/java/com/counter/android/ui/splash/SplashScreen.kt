package com.counter.android.ui.splash

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import com.counter.android.R
import com.counter.android.ui.custom.CounterButton
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.onEach

@Composable
fun SplashScreen(
    viewModel: SplashViewModel,
    navigateToHome: () -> Unit
) {

    LaunchedEffect(Unit) {
        viewModel.oneShotEvents
            .onEach {
                when(it) {
                    SplashViewModel.OneShotEvent.NavigateToHome -> navigateToHome()
                }
            }.collect()
    }

    Scaffold(
        backgroundColor = MaterialTheme.colors.background
    ) {
        ConstraintLayout(
            modifier = Modifier.fillMaxSize()
        ) {
            val (logo, title, description, button) = createRefs()

            Image(
                modifier = Modifier
                    .size(200.dp)
                    .constrainAs(logo) {
                        top.linkTo(parent.top, 80.dp)
                        start.linkTo(parent.start)
                        end.linkTo(parent.end)
                    },
                painter = painterResource(id = R.drawable.ic_icon),
                contentDescription = null
            )

            Text(
                modifier = Modifier
                    .constrainAs(title) {
                        top.linkTo(logo.bottom, 91.dp)
                        start.linkTo(parent.start)
                        end.linkTo(parent.end)
                        width = Dimension.fillToConstraints
                    },
                text = "Counters",
                style = MaterialTheme.typography.h5,
                textAlign = TextAlign.Center
            )
            Text(
                modifier = Modifier
                    .constrainAs(description) {
                        top.linkTo(title.bottom, 20.dp)
                        start.linkTo(parent.start)
                        end.linkTo(parent.end)
                        width = Dimension.fillToConstraints
                    },
                text = "Capture cups of lattes, frapuccinos,\n" +
                    "or anything else that can be counted.",
                style = MaterialTheme.typography.body1,
                textAlign = TextAlign.Center
            )
            CounterButton(
                modifier = Modifier
                    .constrainAs(button) {
                        top.linkTo(description.bottom)
                        bottom.linkTo(parent.bottom)
                        start.linkTo(parent.start)
                        end.linkTo(parent.end)
                    },
                text = "Get started",
                onClick = { viewModel.onNavigateHome(SplashViewModel.UiAction.NavigateToHome) }
            )
        }
    }
}