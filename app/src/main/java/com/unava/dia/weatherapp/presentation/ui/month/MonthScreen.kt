package com.unava.dia.weatherapp.presentation.ui.month

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.rememberImagePainter
import com.unava.dia.weatherapp.R
import com.unava.dia.weatherapp.data.model.future.Forecastday
import com.unava.dia.weatherapp.presentation.ui.theme.Black

@Composable
fun MonthScreen(
    viewModel: MonthViewModel = viewModel(),
    //onBackPressed: () -> Unit
) {
    val state by viewModel.state.collectAsState()
    when (state) {
        is StateForecast.START -> {

        }
        is StateForecast.LOADING -> {
            Box(contentAlignment = Alignment.Center, modifier = Modifier.fillMaxSize()) {
                CircularProgressIndicator()
            }
        }
        is StateForecast.FAILURE -> {
            Box(contentAlignment = Alignment.Center, modifier = Modifier.fillMaxSize()) {
                Text(text = "Something went wrong...", fontSize = 16.sp)
            }
        }
        is StateForecast.SUCCESS -> {
            val weather = (state as StateForecast.SUCCESS).weather
            WeatherList(forecastList = weather.forecast?.forecastday!!)
        }
    }
}

@Composable
fun WeatherList(forecastList: List<Forecastday>) {
    LazyRow(modifier = Modifier.padding(0.dp, 26.dp, 0.dp, 0.dp)) {
        itemsIndexed(items = forecastList) { _, item ->
            WeatherCard(forecast = item)
        }
    }
}

@Composable
fun WeatherCard(forecast: Forecastday) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Date(forecast.date!!)
        IconWeather(forecast.day?.condition?.icon.toString())
        MaxTemp(forecast.day?.maxtemp_c.toString())
        MinTemp(forecast.day?.mintemp_c.toString())
    }
}

@Composable
fun IconWeather(imgUrl: String) {
    val painter = rememberImagePainter(
        data = imgUrl,
        builder = {
            placeholder(R.drawable.weather)
            crossfade(true)
        }
    )

    Image(
        painter = painter,
        //painterResource(R.drawable.weather),
        contentDescription = null,
        modifier = Modifier
            .size(40.dp)
    )
}

@Composable
fun Date(date: String) {
    Text(
        text = date,
        modifier = Modifier.padding(8.dp, 8.dp, 8.dp, 8.dp),
        color = Black,
        style = MaterialTheme.typography.button
    )
}

@Composable
fun MinTemp(minTemp: String) {
    Text(
        text = "$minTemp°C",
        modifier = Modifier.padding(8.dp, 8.dp, 8.dp, 8.dp),
        color = Black,
        style = MaterialTheme.typography.button
    )
}

@Composable
fun MaxTemp(maxTemp: String) {
    Text(
        text = "$maxTemp°C",
        modifier = Modifier.padding(8.dp, 8.dp, 8.dp, 8.dp),
        color = Black,
        style = MaterialTheme.typography.button
    )

}