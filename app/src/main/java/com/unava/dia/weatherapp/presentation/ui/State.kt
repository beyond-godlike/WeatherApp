package com.unava.dia.weatherapp.presentation.ui

import com.unava.dia.weatherapp.data.model.Weather

sealed class State {
    object START : State()
    object LOADING : State()
    data class SUCCESS(val weather: Weather) : State()
    data class FAILURE(val message: String) : State()
}