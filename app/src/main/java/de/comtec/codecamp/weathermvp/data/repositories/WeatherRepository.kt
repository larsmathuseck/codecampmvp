package de.comtec.codecamp.weathermvp.data.repositories

import de.comtec.codecamp.weathermvp.data.network.WeatherService

class WeatherRepository(private val api: WeatherService) {

    fun fetchWeatherData() {
        val weather = api.getWeather()
    }

}