package com.unava.dia.weatherapp.presentation.ui.day

import com.unava.dia.weatherapp.data.model.current.CurrentWeatherResponse

sealed class State {
    object START : State()
    object LOADING : State()
    data class SUCCESS(val weather: CurrentWeatherResponse) : State()
    data class FAILURE(val message: String) : State()
}