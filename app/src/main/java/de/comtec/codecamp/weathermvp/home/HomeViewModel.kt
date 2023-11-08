package de.comtec.codecamp.weathermvp.home

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import de.comtec.codecamp.weathermvp.data.model.WeatherData
import de.comtec.codecamp.weathermvp.data.repositories.NetworkRepository
import de.comtec.codecamp.weathermvp.data.repositories.WeatherRepository
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val weatherRepository: WeatherRepository,
    private val networkRepository: NetworkRepository
) : ViewModel() {

    val weatherData = mutableStateOf<WeatherData?>(null)
    val loading = mutableStateOf(false)

    val internetAccess = networkRepository.networkStatus

    fun fetchWeatherData() {
        viewModelScope.launch {
            loading.value = true
            weatherData.value = weatherRepository.fetchWeatherData()
            delay(3000)
            loading.value = false
        }
    }

}