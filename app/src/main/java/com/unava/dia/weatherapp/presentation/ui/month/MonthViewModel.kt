package com.unava.dia.weatherapp.presentation.ui.month

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.unava.dia.weatherapp.data.local.PrefsHelper
import com.unava.dia.weatherapp.domain.usecase.GetFutureWeatherUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

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
}