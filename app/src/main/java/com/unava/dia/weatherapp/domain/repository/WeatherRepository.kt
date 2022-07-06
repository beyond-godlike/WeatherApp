package com.unava.dia.weatherapp.domain.repository

import com.unava.dia.weatherapp.domain.model.CurrentWeatherResponseDao
import com.unava.dia.weatherapp.domain.model.FutureWeatherResponseDao
import com.unava.dia.weatherapp.data.model.current.CurrentWeatherResponse
import com.unava.dia.weatherapp.data.model.future.FutureWeatherResponse
import javax.inject.Inject

class WeatherRepository @Inject constructor(
    private val currentWeatherDao: CurrentWeatherResponseDao,
    private val futureWeatherDao: FutureWeatherResponseDao,
) : IWeatherRepository {

    override suspend fun getCurrentWeather(id: Long): CurrentWeatherResponse {
        return currentWeatherDao.getCurrentWeather(id)
    }

    override suspend fun insertCurrentWeather(currentWeather: CurrentWeatherResponse): Long? {
        return currentWeatherDao.insert(currentWeather)
    }

    override suspend fun getFutureWeather(id: Long): FutureWeatherResponse {
        return futureWeatherDao.getFutureWeather(id)
    }

    override suspend fun insertFutureWeather(futureWeather: FutureWeatherResponse): Long? {
        return futureWeatherDao.insert(futureWeather)
    }
}