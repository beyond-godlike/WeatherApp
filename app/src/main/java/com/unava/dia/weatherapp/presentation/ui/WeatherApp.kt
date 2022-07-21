package com.unava.dia.weatherapp.presentation.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.pagerTabIndicatorOffset
import com.google.accompanist.pager.rememberPagerState
import com.unava.dia.weatherapp.presentation.ui.day.DayScreen
import com.unava.dia.weatherapp.presentation.ui.month.MonthScreen
import com.unava.dia.weatherapp.presentation.ui.theme.ColorAccent
import com.unava.dia.weatherapp.presentation.ui.theme.ColorPrimaryDark
import kotlinx.coroutines.launch


@ExperimentalPagerApi
@Composable
fun WeatherApp() {
    Surface(modifier = Modifier.fillMaxSize()) {
        val pagerState = rememberPagerState()
        val tabs = listOf("CURRENT", "FORECAST")
        val state by remember { mutableStateOf(0) }
        val scope = rememberCoroutineScope()

        // TAB
        Column {
            TabRow(selectedTabIndex = state,
                indicator = {
                    TabRowDefaults.Indicator(
                        Modifier.pagerTabIndicatorOffset(pagerState, it)
                    )
                },
            ) {
                tabs.forEachIndexed { index, text ->
                    Tab(
                        selected = pagerState.currentPage == index,
                        onClick = {
                            scope.launch {
                                pagerState.animateScrollToPage(index)
                            }
                        },
                        text = {
                            Text(
                                text = text,
                                color = if (pagerState.currentPage == index) ColorPrimaryDark else ColorAccent
                            )
                        }
                    )
                }
            }

            // PAGER
            HorizontalPager(
                count = tabs.size,
                state = pagerState,
            ) { index ->
                Column(
                    modifier = Modifier.fillMaxSize(),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    when (index) {
                        0 -> DayScreen(viewModel = hiltViewModel())
                        1 -> MonthScreen(viewModel = hiltViewModel())
                    }
                }
            }
        }
    }
}