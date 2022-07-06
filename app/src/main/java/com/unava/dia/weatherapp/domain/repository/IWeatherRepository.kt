package com.unava.dia.weatherapp.domain.repository

import com.unava.dia.weatherapp.data.model.current.CurrentWeatherResponse
import com.unava.dia.weatherapp.data.model.future.FutureWeatherResponse

interface IWeatherRepository {
    suspend fun getCurrentWeather(id: Long) : CurrentWeatherResponse
    suspend fun insertCurrentWeather(currentWeather: CurrentWeatherResponse) : Long?
    suspend fun getFutureWeather(id: Long) : FutureWeatherResponse
    suspend fun insertFutureWeather(futureWeather: FutureWeatherResponse) : Long?
}