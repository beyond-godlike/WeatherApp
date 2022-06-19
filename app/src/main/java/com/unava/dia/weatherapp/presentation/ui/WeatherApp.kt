package com.unava.dia.weatherapp.presentation.ui

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.unava.dia.weatherapp.presentation.ui.day.DayScreen
import com.unava.dia.weatherapp.presentation.ui.month.MonthScreen

@Composable
fun WeatherApp(appState: WeatherAppState = rememberWeatherAppState()){
    NavHost(
        navController = appState.controller,
        startDestination = Screen.Day.route
    ) {
        composable(Screen.Day.route) {
            DayScreen(
                viewModel = hiltViewModel(),
                navigateToMonth = { city ->
                    appState.navigateToMonth(city.toString())
                },
            )
        }

        composable(Screen.Month.route) {
            MonthScreen(
                viewModel = hiltViewModel(),
                onBackPressed = appState::navigateBack,
                city = it.arguments?.getString("city") ?: "London",
            )
        }
    }

}