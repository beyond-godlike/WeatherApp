package com.unava.dia.weatherapp.domain.model

import androidx.room.*
import com.unava.dia.weatherapp.data.model.current.CurrentWeatherResponse
import com.unava.dia.weatherapp.data.model.future.FutureWeatherResponse

@Dao
interface FutureWeatherResponseDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(weather: FutureWeatherResponse): Long?

    @Update
    suspend fun update(weather: FutureWeatherResponse)

    @Delete
    suspend fun delete(weather: CurrentWeatherResponse)

    @Query("SELECT * from futureWeather")
    fun getFutureWeatherList(): List<FutureWeatherResponse>

    @Query("SELECT * FROM futureWeather WHERE id =:id")
    fun getFutureWeather(id: Long): FutureWeatherResponse
}