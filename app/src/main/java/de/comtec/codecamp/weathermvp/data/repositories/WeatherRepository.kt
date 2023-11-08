package de.comtec.codecamp.weathermvp.data.repositories

import de.comtec.codecamp.weathermvp.data.model.WeatherData
import de.comtec.codecamp.weathermvp.data.network.WeatherService

class WeatherRepository(private val api: WeatherService) {

    suspend fun fetchWeatherData(): WeatherData? {
        val resp = api.getWeather()
        val data = if (resp.isSuccessful) {
             resp.body()
        } else {
            // Handle empty response
            // TODO
            null
        }
        return data
    }

}