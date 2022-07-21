package com.unava.dia.weatherapp.data.remote

import android.content.Context
import android.util.Log
import androidx.hilt.work.HiltWorker
import androidx.work.Worker
import androidx.work.WorkerParameters
import com.unava.dia.weatherapp.data.local.prefs.PrefsHelper
import com.unava.dia.weatherapp.domain.di.DefaultDispatcher
import com.unava.dia.weatherapp.domain.repository.WeatherRepository
import com.unava.dia.weatherapp.domain.usecase.GetCurrentWeatherUseCase
import com.unava.dia.weatherapp.domain.usecase.GetFutureWeatherUseCase
import dagger.assisted.Assisted
import dagger.assisted.AssistedInject
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@HiltWorker
class WeatherWorker @AssistedInject constructor(
    @Assisted val context: Context,
    @Assisted params: WorkerParameters,
    private val currentUseCase: GetCurrentWeatherUseCase,
    private val futureUseCase: GetFutureWeatherUseCase,
    private val shared: PrefsHelper,
    @DefaultDispatcher
    val dispatcher: CoroutineDispatcher,
    private val repository: WeatherRepository,
) : Worker(context, params) {

    private var id: Long? = null

    override fun doWork(): Result {
        getCurrentWeather()
        getFutureWeather()
        return Result.success()
    }

    private fun getCurrentWeather() {
        CoroutineScope(dispatcher).launch {
            try {
                val response = currentUseCase.getCurrentWeather(shared.getLastCity())
                if (response != null) {
                    id = repository.insertCurrentWeather(response)
                    id?.let { shared.setId(it) }
                    Log.d("w", "done")
                    Log.d("w", id.toString())
                }
            } catch (e: Exception) {
                Log.d("w", e.localizedMessage)
            }
        }
    }

    private fun getFutureWeather() {
        CoroutineScope(dispatcher).launch {
            try {
                val response = futureUseCase.getFutureWeather(shared.getLastCity(), 7)
                if (response != null) {
                    id = repository.insertFutureWeather(response)
                }
            } catch (e: Exception) {

            }
        }
    }

}