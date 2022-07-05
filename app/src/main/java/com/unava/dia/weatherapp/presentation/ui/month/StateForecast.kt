package com.unava.dia.weatherapp.presentation.ui.month

import com.unava.dia.weatherapp.data.model.future.FutureWeatherResponse

sealed class StateForecast{
    object START : StateForecast()
    object LOADING : StateForecast()
    data class SUCCESS(val weather: FutureWeatherResponse) : StateForecast()
    data class FAILURE(val message: String) : StateForecast()
}