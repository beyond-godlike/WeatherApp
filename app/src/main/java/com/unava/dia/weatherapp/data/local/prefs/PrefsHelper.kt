package com.unava.dia.weatherapp.data.local.prefs

interface PrefsHelper {
    fun isFirstRun(): Boolean

    fun setFirstRun(enable: Boolean = false)

    fun saveLastCity(cityName: String)

    fun getLastCity(): String

    fun getId() : Long

    fun setId(id: Long)
}