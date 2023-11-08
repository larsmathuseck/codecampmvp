package de.comtec.codecamp.weathermvp.home

import android.health.connect.datatypes.units.Temperature
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel


@Preview
@Composable
fun HomeScreen(viewModel: HomeViewModel = hiltViewModel()) {

    val weather = viewModel.weatherData.value

    LaunchedEffect(key1 = Unit, block = {
        viewModel.fetchWeatherData()
    })

    Column {
        TemperatureInfo(weather?.current?.temperature ?: 20.0)
        PrecipitationInfo(weather?.current?.precipitationProbability ?: 0.0)
    }
}

@Preview
@Composable
fun TemperatureInfo(temperature: Double = 18.0) {
    Card(modifier = Modifier.padding(32.dp)) {
        Text(text = "$temperature °C")
    }
}

@Preview
@Composable
fun PrecipitationInfo(precipitation: Double = 0.0) {
    Card(modifier = Modifier.padding(32.dp)) {
        Text(text = "$precipitation %")
    }
}

