package com.unava.dia.weatherapp.domain.usecase

import com.unava.dia.weatherapp.data.api.WeatherApi
import com.unava.dia.weatherapp.data.model.future.FutureWeatherResponse

class GetFutureWeatherUseCase(
    private val api: WeatherApi
) {
    suspend fun getFutureWeather(lastCity: String, days: Int) : FutureWeatherResponse {
        return api.getFutureWeatherAsync(lastCity, days)
    }
}