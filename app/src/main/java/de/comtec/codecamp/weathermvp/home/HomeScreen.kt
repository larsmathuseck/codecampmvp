package de.comtec.codecamp.weathermvp.home

import android.health.connect.datatypes.units.Temperature
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
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
    val loading = viewModel.loading.value

    LaunchedEffect(key1 = Unit, block = {
        viewModel.fetchWeatherData()
    })

    Column {

        if (loading) {
            Column {
                Text(text = "Loading...")
                Spacer(modifier = Modifier.size(16.dp))
                CircularProgressIndicator()
            }
        } else {
            TemperatureInfo(weather?.current?.temperature ?: 20.0)
            PrecipitationInfo(weather?.current?.precipitationProbability ?: 0.0)
            Button(onClick = {
                viewModel.fetchWeatherData()
            }) {
                Text(text = "Refresh")
            }
        }

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

