package com.unava.dia.weatherapp.data.model

import com.unava.dia.weatherapp.data.model.current.CurrentWeatherResponse
import com.unava.dia.weatherapp.data.model.future.FutureWeatherResponse

data class Weather(
    var responseCurrent: CurrentWeatherResponse,
    var responseFuture: FutureWeatherResponse
)
