package com.unava.dia.weatherapp.data.model.future

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.unava.dia.weatherapp.data.model.current.Current
import com.unava.dia.weatherapp.data.model.current.Location

@Entity(tableName = "futureWeather")
data class FutureWeatherResponse(
    @PrimaryKey var id: Int? = null,
    var location : Location? = null,
    var current : Current? = null,
    var forecast : Forecast? = null
)