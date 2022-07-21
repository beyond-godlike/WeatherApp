package com.unava.dia.weatherapp.presentation.ui

import android.graphics.Color
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.unava.dia.weatherapp.data.local.prefs.PrefsHelper
import com.unava.dia.weatherapp.data.model.Weather
import com.unava.dia.weatherapp.domain.repository.AppDatabase
import com.unava.dia.weatherapp.domain.usecase.GetCurrentWeatherUseCase
import com.unava.dia.weatherapp.domain.usecase.GetFutureWeatherUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject
import kotlin.math.abs


@HiltViewModel
class MainViewModel @Inject constructor(
    private val currentWeatherUseCase: GetCurrentWeatherUseCase,
    private val futureWeatherUseCase: GetFutureWeatherUseCase,
    private val database: AppDatabase,
    private val prefsHelper: PrefsHelper,
) : ViewModel() {
    val state = MutableStateFlow<State>(State.START)
    var city by mutableStateOf("")

    private val _isLoading = MutableStateFlow(true)
    val isLoading = _isLoading.asStateFlow()

    init {
        fetchWeather()
    }

    fun fetchWeather() {
        state.value = State.LOADING

        viewModelScope.launch {
            try {
                val responseCurrent =
                    currentWeatherUseCase.getCurrentWeather(prefsHelper.getLastCity())
                val responseFuture =
                    futureWeatherUseCase.getFutureWeather(prefsHelper.getLastCity(), 7)
                val response = Weather(responseCurrent, responseFuture)

                state.value = State.SUCCESS(response)

            } catch (e: Exception) {
                val responseCurrent =
                    database.currentWeatherDao().getCurrentWeather(prefsHelper.getId())
                val responseFuture =
                    database.futureWeatherDao().getFutureWeather(prefsHelper.getId())

                if (responseCurrent != null && responseFuture != null) {
                    val response = Weather(responseCurrent, responseFuture)

                    state.value = State.SUCCESS(response)
                } else {
                    state.value = State.FAILURE("response is null")
                }
            } finally {
                _isLoading.value = false
            }
        }
    }

    fun saveCity() {
        prefsHelper.saveLastCity(city)
    }


    private fun fib(n: Int): Int {
        return if (n <= 1) n else fib(n - 1) + fib(n - 2)
    }

    fun countRGBStroke(avg: Float): Int {
        val hsv = FloatArray(3)
        hsv[0] = 359.0f - (200.0f + (fib(avg.toInt()) / 100.0f))
        hsv[1] = abs(avg) / 100
        hsv[2] = 0.8f


        return Color.HSVToColor(hsv)
    }

    fun countRGB(avg: Float): Int {
        val hsv = FloatArray(3)
        hsv[0] = 359.0f - (200.0f + (fib(avg.toInt()) / 100.0f))
        hsv[1] = abs(avg) / 100
        hsv[2] = 1.0f

        return Color.HSVToColor(hsv)
    }
}