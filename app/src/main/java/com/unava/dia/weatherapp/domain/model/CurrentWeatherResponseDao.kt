package com.unava.dia.weatherapp.domain.model

import androidx.room.*
import com.unava.dia.weatherapp.data.model.current.CurrentWeatherResponse

@Dao
interface CurrentWeatherResponseDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(weather: CurrentWeatherResponse): Long?

    @Update
    suspend fun update(weather: CurrentWeatherResponse)

    @Delete
    suspend fun delete(weather: CurrentWeatherResponse)

    @Query("SELECT * from currentWeather")
    fun getCurrentWeatherList(): List<CurrentWeatherResponse>

    @Query("SELECT * FROM currentWeather WHERE id =:id")
    fun getCurrentWeather(id: Long): CurrentWeatherResponse
}