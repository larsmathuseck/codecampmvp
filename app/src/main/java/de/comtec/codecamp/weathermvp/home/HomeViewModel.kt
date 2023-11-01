package de.comtec.codecamp.weathermvp.home

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import de.comtec.codecamp.weathermvp.data.model.WeatherData
import de.comtec.codecamp.weathermvp.data.repositories.WeatherRepository
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(private val weatherRepository: WeatherRepository) : ViewModel() {

    val weatherData = mutableStateOf<WeatherData?>(null)

    private fun fetchWeatherData() {
        weatherRepository.fetchWeatherData()
    }

}