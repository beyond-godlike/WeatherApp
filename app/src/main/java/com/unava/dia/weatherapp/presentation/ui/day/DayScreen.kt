package com.unava.dia.weatherapp.presentation.ui.day

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.unava.dia.weatherapp.R
import com.unava.dia.weatherapp.presentation.ui.theme.WeatherAppTheme

@Composable
fun DayScreen(
    viewModel: DayViewModel = viewModel(),
    navigateToMonth: (String) -> Unit,
) {
    Column(
        verticalArrangement = Arrangement.Top,
        modifier = Modifier.fillMaxSize()
    ) {
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            LabelCity()
            EditTextCity()
            Spacer(modifier = Modifier.width(16.dp))
            ButtonOk()
        }
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = Modifier.fillMaxSize()
        ) {
            WeatherImage()
            Spacer(modifier = Modifier.height(16.dp))
            LabelCityName()
            Spacer(modifier = Modifier.height(16.dp))
            LabelTemp()
            Spacer(modifier = Modifier.height(16.dp))
            LabelF()
        }
    }
}

@Preview
@Composable
fun DefaultPreview() {
    WeatherAppTheme {
        Column(
            verticalArrangement = Arrangement.Top,
            modifier = Modifier.fillMaxSize()
        ) {
            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.fillMaxWidth()
            ) {
                LabelCity()
                EditTextCity()
                ButtonOk()
            }
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center,
                modifier = Modifier.fillMaxSize()
            ) {
                WeatherImage()
                Spacer(modifier = Modifier.height(16.dp))
                LabelCityName()
                Spacer(modifier = Modifier.height(16.dp))
                LabelTemp()
                Spacer(modifier = Modifier.height(16.dp))
                LabelF()
            }
        }
    }
}

@Composable
fun LabelCity() {
    Text(
        text = "City",
        color = MaterialTheme.colors.secondaryVariant,
        style = MaterialTheme.typography.button
    )
}

@Composable
fun EditTextCity() {
    var cityFieldState by remember {
        mutableStateOf("")
    }

    OutlinedTextField(
        modifier = Modifier
            .padding(30.dp),
        value = cityFieldState,
        onValueChange = {
            cityFieldState = it
        },
        shape = RoundedCornerShape(8.dp),
    )

}

@Composable
fun ButtonOk() {
    TextButton(
        onClick = { /*TODO*/ },
        modifier = Modifier.padding(8.dp)
    ) {
        Text(text = "OK")
    }
}

@Composable
fun WeatherImage() {
    Image(
        painter = painterResource(R.drawable.weather),
        contentDescription = null,
        modifier = Modifier
            .size(40.dp)
            .clip(CircleShape)
            .border(1.5.dp, MaterialTheme.colors.secondary, CircleShape)
    )

}

@Composable
fun LabelCityName() {
    Text(
        text = "London",
        color = MaterialTheme.colors.secondaryVariant,
        style = MaterialTheme.typography.h3
    )

}

@Composable
fun LabelTemp() {
    Text(
        text = "10°C",
        color = MaterialTheme.colors.secondaryVariant,
        style = MaterialTheme.typography.h4
    )
}

@Composable
fun LabelF() {
    Text(
        text = "rain",
        color = MaterialTheme.colors.secondaryVariant,
        style = MaterialTheme.typography.h5
    )

}