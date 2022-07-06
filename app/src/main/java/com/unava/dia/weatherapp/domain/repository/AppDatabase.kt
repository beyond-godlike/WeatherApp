package com.unava.dia.weatherapp.domain.repository

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.unava.dia.weatherapp.domain.model.CurrentWeatherResponseDao
import com.unava.dia.weatherapp.domain.model.FutureWeatherResponseDao
import com.unava.dia.weatherapp.data.model.current.CurrentWeatherResponse
import com.unava.dia.weatherapp.data.model.future.FutureWeatherResponse

@Database(entities = [CurrentWeatherResponse::class, FutureWeatherResponse::class],
    version = 1,
    exportSchema = false)
@TypeConverters(
    WeatherConverter::class
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun currentWeatherDao(): CurrentWeatherResponseDao
    abstract fun futureWeatherDao(): FutureWeatherResponseDao
}