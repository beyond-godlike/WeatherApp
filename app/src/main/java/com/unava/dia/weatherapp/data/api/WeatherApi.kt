package com.unava.dia.weatherapp.data.api

import com.unava.dia.weatherapp.data.model.current.CurrentWeatherResponse
import com.unava.dia.weatherapp.data.model.future.FutureWeatherResponse
import kotlinx.coroutines.flow.Flow
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherApi {
    /**
     * запрос для получения текущей погоды
     */

    @GET("current.json")
    suspend fun getCurrentWeatherAsync(
        @Query("q") q:String
    ): CurrentWeatherResponse

    /**
     * запрос для получения прогноза погоды
     */
    @GET("forecast.json")
    suspend fun getFutureWeatherAsync(
        @Query("q") location: String,
        @Query("days") days: Int,
    ): Flow<FutureWeatherResponse>
}