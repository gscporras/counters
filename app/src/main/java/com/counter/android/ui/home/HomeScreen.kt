package com.counter.android.ui.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import com.counter.android.ui.custom.*
import com.counter.android.ui.theme.black80
import com.google.accompanist.swiperefresh.SwipeRefresh
import com.google.accompanist.swiperefresh.rememberSwipeRefreshState
import com.skydoves.whatif.whatIfNotNullOrEmpty

@Composable
fun HomeScreen(
    viewModel: HomeViewModel,
    navigateToCreateCounter: () -> Unit
) {
    LaunchedEffect(Unit) {
        viewModel.getCounters()
    }

    HomeContent(
        viewModel = viewModel,
        navigateToCreateCounter = navigateToCreateCounter
    )
}

@Composable
fun HomeContent(
    viewModel: HomeViewModel,
    navigateToCreateCounter: () -> Unit
) {
    val search = remember { mutableStateOf("") }
    val showSearch = remember { mutableStateOf(false) }

    val counters = viewModel.counters.collectAsState()
    val filteredCounters = viewModel.filteredCounters.collectAsState()
    val isRefreshing by viewModel.isRefreshing.collectAsState()
    val isLoading by viewModel.isLoading.collectAsState()
    val isLoadingFiltered by viewModel.isLoadingFiltered.collectAsState()

    Scaffold(
        modifier = Modifier
            .fillMaxSize(),
        backgroundColor = MaterialTheme.colors.background,
        topBar = {
            if(showSearch.value) {
                TopSearchCard(
                    state = search,
                    onBack = {
                        showSearch.value = false
                        search.value = ""
                        viewModel.refresh()
                    },
                    onSearch = { viewModel.filterCounters(search.value) }
                )
            } else {
                SearchCard(
                    modifier = Modifier
                        .padding(8.dp),
                    onClick = { showSearch.value = true }
                )
            }
        }
    ) {
        ConstraintLayout(
            modifier = Modifier
                .fillMaxSize()
        ) {
            val (searchCard, emptyList, list, progress, buttonAdd) = createRefs()

            counters.value.ifEmpty {
                CounterEmpty(
                    modifier = Modifier
                        .constrainAs(emptyList) {
                            top.linkTo(parent.top)
                            bottom.linkTo(parent.bottom)
                            start.linkTo(parent.start)
                            end.linkTo(parent.end)
                        }
                )
            }
            counters.value.whatIfNotNullOrEmpty {
                SwipeRefresh(
                    modifier = Modifier
                        .constrainAs(list) {
                            top.linkTo(parent.top)
                            bottom.linkTo(parent.bottom)
                            start.linkTo(parent.start)
                            end.linkTo(parent.end)
                            width = Dimension.fillToConstraints
                            height = Dimension.fillToConstraints
                        },
                    state = rememberSwipeRefreshState(isRefreshing),
                    onRefresh = { viewModel.refresh() }
                ) {
                    LazyColumn(
                        modifier = Modifier
                            .fillMaxSize()
                    ) {
                        item {
                            CounterStickyItem(counters = counters.value)
                        }
                        items(counters.value) { item ->
                            CounterItem(
                                title = item.title,
                                count = item.count,
                                decrement = { viewModel.decrement(item) },
                                increment = { viewModel.increment(item) }
                            )
                        }
                    }
                }
            }
            if(isLoading) {
                CounterProgress(
                    modifier = Modifier
                        .constrainAs(progress) {
                            top.linkTo(parent.top)
                            bottom.linkTo(parent.bottom)
                            start.linkTo(parent.start)
                            end.linkTo(parent.end)
                        }
                )
            }
            CounterIconButton(
                modifier = Modifier
                    .constrainAs(buttonAdd) {
                        bottom.linkTo(parent.bottom, 32.dp)
                        start.linkTo(parent.start)
                        end.linkTo(parent.end)
                    },
                onClick = { navigateToCreateCounter() }
            )
            if(showSearch.value) {
                Card(
                    modifier = Modifier
                        .constrainAs(searchCard) {
                            top.linkTo(parent.top)
                            bottom.linkTo(parent.bottom)
                            start.linkTo(parent.start)
                            end.linkTo(parent.end)
                            width = Dimension.fillToConstraints
                            height = Dimension.fillToConstraints
                        },
                    backgroundColor = black80
                ) {
                    filteredCounters.value.whatIfNotNullOrEmpty {
                        SwipeRefresh(
                            modifier = Modifier
                                .constrainAs(list) {
                                    top.linkTo(parent.top)
                                    bottom.linkTo(parent.bottom)
                                    start.linkTo(parent.start)
                                    end.linkTo(parent.end)
                                    width = Dimension.fillToConstraints
                                    height = Dimension.fillToConstraints
                                },
                            state = rememberSwipeRefreshState(isRefreshing),
                            onRefresh = { viewModel.refresh() }
                        ) {
                            LazyColumn(
                                modifier = Modifier
                                    .background(MaterialTheme.colors.background)
                                    .fillMaxSize()
                            ) {
                                item {
                                    CounterStickyItem(counters = filteredCounters.value)
                                }
                                items(filteredCounters.value) { item ->
                                    CounterItem(
                                        title = item.title,
                                        count = item.count,
                                        decrement = { viewModel.decrementFiltered(item) },
                                        increment = { viewModel.incrementFiltered(item) }
                                    )
                                }
                            }
                        }
                    }
                }
                filteredCounters.value.ifEmpty {
                    if(search.value.isNotEmpty()) {
                        CounterFilteredEmpty(
                            modifier = Modifier
                                .constrainAs(emptyList) {
                                    top.linkTo(parent.top)
                                    bottom.linkTo(parent.bottom)
                                    start.linkTo(parent.start)
                                    end.linkTo(parent.end)
                                }
                        )
                    }
                }
                if(isLoadingFiltered) {
                    CounterProgress(
                        modifier = Modifier
                            .constrainAs(progress) {
                                top.linkTo(parent.top)
                                bottom.linkTo(parent.bottom)
                                start.linkTo(parent.start)
                                end.linkTo(parent.end)
                            }
                    )
                }
            }
        }
    }
}