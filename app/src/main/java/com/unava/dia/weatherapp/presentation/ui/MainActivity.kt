package com.unava.dia.weatherapp.presentation.ui

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.core.content.ContextCompat
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.core.view.WindowCompat
import androidx.work.ExistingPeriodicWorkPolicy
import androidx.work.PeriodicWorkRequest
import androidx.work.WorkManager
import com.google.accompanist.pager.ExperimentalPagerApi
import com.unava.dia.weatherapp.R
import com.unava.dia.weatherapp.data.remote.WeatherWorker
import com.unava.dia.weatherapp.presentation.ui.theme.WeatherAppTheme
import dagger.hilt.android.AndroidEntryPoint
import java.util.concurrent.TimeUnit

@Suppress("DEPRECATION")
@ExperimentalPagerApi
@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val viewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        WindowCompat.setDecorFitsSystemWindows(window, false)

        installSplashScreen().apply {
            setKeepVisibleCondition {
                viewModel.isLoading.value
            }
        }

        if(isInternetAvailable(this)) {
            startWorkManager()
        }

        setContent {
            WeatherAppTheme {
                WeatherApp()
            }
        }
        showSystemUI()
    }

    private fun isInternetAvailable(context: Context): Boolean {
        var result = false
        val connectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            val networkCapabilities = connectivityManager.activeNetwork ?: return false
            val actNw = connectivityManager.getNetworkCapabilities(networkCapabilities) ?: return false
            result = when {
                actNw.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> true
                actNw.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> true
                actNw.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET) -> true
                else -> false
            }
        } else {
            connectivityManager.run {
                connectivityManager.activeNetworkInfo?.run {
                    result = when (type) {
                        ConnectivityManager.TYPE_WIFI -> true
                        ConnectivityManager.TYPE_MOBILE -> true
                        ConnectivityManager.TYPE_ETHERNET -> true
                        else -> false
                    }
                }
            }
        }
        return result
    }

    private fun showSystemUI() {
        WindowCompat.setDecorFitsSystemWindows(window, true)
        window.statusBarColor = ContextCompat.getColor(this, R.color.primaryDark)
        window.navigationBarColor = ContextCompat.getColor(this, R.color.black)
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

