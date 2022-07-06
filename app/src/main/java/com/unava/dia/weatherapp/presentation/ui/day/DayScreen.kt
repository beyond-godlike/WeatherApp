package com.unava.dia.weatherapp.presentation.ui.day

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
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
import com.unava.dia.weatherapp.data.model.current.CurrentWeatherResponse
import com.unava.dia.weatherapp.presentation.ui.theme.Black
import com.unava.dia.weatherapp.presentation.ui.theme.ColorPrimary
import com.unava.dia.weatherapp.presentation.ui.theme.ColorPrimaryDark
import com.unava.dia.weatherapp.presentation.ui.theme.Grey

@Composable
fun DayScreen(
    viewModel: DayViewModel = viewModel(),
    //onBackPressed: () -> Unit
    //navigateToMonth: (String) -> Unit,
) {
    val state by viewModel.state.collectAsState()
    when (state) {
        is State.START -> {

        }
        is State.LOADING -> {
            Box(contentAlignment = Alignment.Center, modifier = Modifier.fillMaxSize()) {
                CircularProgressIndicator()
            }
        }
        is State.FAILURE -> {
            Box(contentAlignment = Alignment.Center, modifier = Modifier.fillMaxSize()) {
                Text(text = "Something went wrong...", fontSize = 16.sp)
            }
            viewModel.city = "London"
            viewModel.saveCity()
        }
        is State.SUCCESS -> {
            val weather = (state as State.SUCCESS).weather
            Day(weather, viewModel)
        }
    }
}

@Composable
fun Day(weather: CurrentWeatherResponse, viewModel: DayViewModel) {
    Column(
        verticalArrangement = Arrangement.Top,
        modifier = Modifier
            .fillMaxSize()
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            LabelCity()
            EditTextCity(viewModel)
            Spacer(modifier = Modifier.width(120.dp))
            ButtonOk(viewModel)
        }
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = Modifier.fillMaxSize()
        ) {
            WeatherImage("https:" + weather.current!!.condition!!.icon!!)
            Spacer(modifier = Modifier.height(16.dp))
            LabelCityName(weather.location?.name ?: "London")
            Spacer(modifier = Modifier.height(16.dp))
            LabelTemp(weather.current?.feelslike_c.toString())
            Spacer(modifier = Modifier.height(16.dp))
            LabelWeather(weather.current?.condition?.text.toString())
        }
    }
}

@Composable
fun LabelCity() {
    Text(
        text = "City",
        modifier = Modifier.padding(26.dp, 26.dp, 8.dp, 8.dp),
        color = Black,
        style = MaterialTheme.typography.button
    )
}

@Composable
fun EditTextCity(viewModel: DayViewModel) {
    OutlinedTextField(
        modifier = Modifier
            .padding(8.dp, 26.dp, 8.dp, 8.dp),
        value = viewModel.city,
        onValueChange = {
            viewModel.city = it
        },
        shape = RoundedCornerShape(8.dp),
    )

}

@Composable
fun ButtonOk(viewModel: DayViewModel) {
    Box(modifier = Modifier.fillMaxWidth()) {
        Button(
            onClick = {
                viewModel.saveCity()
                viewModel.fetchWeather()
            },
            modifier = Modifier
                .align(Alignment.TopEnd)
                .padding(26.dp, 26.dp),
            elevation = ButtonDefaults.elevation(10.dp)
        ) {
            Text(text = "OK")
        }
    }
}

@Composable
fun WeatherImage(imgUrl: String) {
    val painter = rememberImagePainter(
        data = imgUrl,
        builder = {
            placeholder(R.drawable.weather)
            crossfade(true)
        }
    )

    Image(
        painter = painter,
        contentDescription = null,
        modifier = Modifier
            .size(40.dp)
    )

}

@Composable
fun LabelCityName(city: String) {
    Text(
        text = city,
        modifier = Modifier.padding(5.dp),
        color = Black,
        style = MaterialTheme.typography.h4
    )

}

@Composable
fun LabelTemp(temp: String) {
    Text(
        text = "$temp°C",
        color = ColorPrimaryDark,
        modifier = Modifier.padding(5.dp),
        style = MaterialTheme.typography.h5
    )
}

@Composable
fun LabelWeather(weather: String) {
    Text(
        text = weather,
        color = Grey,
        style = MaterialTheme.typography.h6
    )

}