package com.unava.dia.weatherapp.presentation.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.core.view.WindowCompat
import androidx.work.ExistingPeriodicWorkPolicy
import androidx.work.PeriodicWorkRequest
import androidx.work.WorkManager
import com.google.accompanist.pager.ExperimentalPagerApi
import com.unava.dia.weatherapp.data.remote.WeatherWorker
import com.unava.dia.weatherapp.presentation.ui.theme.WeatherAppTheme
import dagger.hilt.android.AndroidEntryPoint
import java.util.concurrent.TimeUnit

@ExperimentalPagerApi
@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        WindowCompat.setDecorFitsSystemWindows(window, false)

        startWorkManager()

        setContent {
            WeatherAppTheme {
                WeatherApp()
            }
        }
    }
    private fun startWorkManager() {
        val wm = WorkManager.getInstance(this)
        wm.enqueueUniquePeriodicWork(
            "updateWeather",
            ExistingPeriodicWorkPolicy.REPLACE,
            PeriodicWorkRequest
                .Builder(WeatherWorker::class.java, 30L, TimeUnit.MINUTES)
                .build())
    }
}

