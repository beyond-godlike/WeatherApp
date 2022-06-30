package com.unava.dia.weatherapp.data.model.current

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "currentWeather")
data class CurrentWeatherResponse(
    @PrimaryKey var id: Int? = null,
    var location : Location? = null,
    var current : Current? = null
)