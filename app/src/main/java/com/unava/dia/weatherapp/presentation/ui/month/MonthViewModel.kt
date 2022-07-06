package com.unava.dia.weatherapp.presentation.ui.month

import android.graphics.Color
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.unava.dia.weatherapp.data.local.PrefsHelper
import com.unava.dia.weatherapp.domain.usecase.GetFutureWeatherUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject
import kotlin.math.abs

@HiltViewModel
class MonthViewModel @Inject constructor(
    private val futureWeatherUseCase: GetFutureWeatherUseCase,
    private val prefsHelper: PrefsHelper,
) : ViewModel() {
    val state = MutableStateFlow<StateForecast>(StateForecast.START)

    init {
        fetchWeather()
    }

    private fun fetchWeather() {
        state.value = StateForecast.LOADING
        viewModelScope.launch {
            try {
                val response = futureWeatherUseCase.getFutureWeather(prefsHelper.getLastCity(), 7)
                state.value = StateForecast.SUCCESS(response)
            } catch (e: Exception) {
                state.value = StateForecast.FAILURE(e.localizedMessage)
            }
        }
    }

    fun fib(n: Int): Int {
        return if (n <= 1) n else fib(n - 1) + fib(n - 2)
    }

    fun countRGBStroke(avg: Float): Int {
        val hsv = FloatArray(3)
        hsv[0] = 359.0f - (200.0f + (fib(avg.toInt()) / 100.0f ))
        hsv[1] = abs(avg) / 100
        hsv[2] = 0.8f


        return Color.HSVToColor(hsv)
    }

    fun countRGB(avg: Float): Int {
        val hsv = FloatArray(3)
        hsv[0] = 359.0f - (200.0f + (fib(avg.toInt()) / 100.0f ))
        hsv[1] = abs(avg) / 100
        hsv[2] = 1.0f

        return Color.HSVToColor(hsv)
    }

}