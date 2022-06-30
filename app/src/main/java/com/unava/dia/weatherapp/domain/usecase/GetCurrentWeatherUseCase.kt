package com.unava.dia.weatherapp.domain.usecase

import com.unava.dia.weatherapp.data.api.WeatherApi
import com.unava.dia.weatherapp.data.model.current.CurrentWeatherResponse
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetCurrentWeatherUseCase (
    private val api: WeatherApi
) {
    suspend fun getCurrentWeather(s: String) : CurrentWeatherResponse {
        return api.getCurrentWeatherAsync(s)
    }
}