package com.unava.dia.weatherapp.presentation.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Tab
import androidx.compose.material.TabRow
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.rememberPagerState
import com.unava.dia.weatherapp.presentation.ui.day.DayScreen
import com.unava.dia.weatherapp.presentation.ui.month.MonthScreen
import kotlinx.coroutines.launch


@ExperimentalPagerApi
@Composable
fun WeatherApp() {
    val pagerState = rememberPagerState(pageCount = 2)
    val tabs = listOf("CURRENT", "FUTURE")
    val tabIndex = pagerState.currentPage
    val coroutineScope = rememberCoroutineScope()
    // TAB
    TabRow(selectedTabIndex = tabIndex,
        modifier = Modifier.padding(top = 20.dp)) {
        tabs.forEachIndexed { index, text ->
            Tab(selected = tabIndex == index, onClick = {
                coroutineScope.launch {
                    pagerState.animateScrollToPage(index)
                }
            }, text = { Text(text = text) })
        }
    }

    // PAGER
    HorizontalPager(
        state = pagerState
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