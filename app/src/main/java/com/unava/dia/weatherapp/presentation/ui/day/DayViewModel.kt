package com.unava.dia.weatherapp.presentation.ui.day

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.unava.dia.weatherapp.data.local.PrefsHelper
import com.unava.dia.weatherapp.domain.usecase.GetCurrentWeatherUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DayViewModel @Inject constructor(
    private val currentWeatherUseCase: GetCurrentWeatherUseCase,
    private val prefsHelper: PrefsHelper,
) : ViewModel() {

    val state = MutableStateFlow<State>(State.START)
    var city by mutableStateOf("")

    init {
        fetchWeather()
    }

    fun fetchWeather() {
        state.value = State.LOADING
        viewModelScope.launch {
            try {
                val response = currentWeatherUseCase.getCurrentWeather(prefsHelper.getLastCity())
                state.value = State.SUCCESS(response)
            } catch (e: Exception) {
                state.value = State.FAILURE(e.localizedMessage)
            }
        }
    }

    fun saveCity() {
        prefsHelper.saveLastCity(city)
    }
}