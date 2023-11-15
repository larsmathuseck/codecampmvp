package de.comtec.codecamp.weathermvp.data.repositories

import de.comtec.codecamp.weathermvp.data.database.WeatherDao
import de.comtec.codecamp.weathermvp.data.model.WeatherData
import de.comtec.codecamp.weathermvp.data.network.WeatherService

class WeatherRepository(private val api: WeatherService, private val weatherDao: WeatherDao) {

    val weatherFlow = weatherDao.getWeather()

    suspend fun fetchWeatherData() {
        val resp = api.getWeather()
        val data = if (resp.isSuccessful) {
            resp.body()
        } else {
            // Handle empty response
            // TODO
            null
        }
        val databaseData = data?.current?.toWeatherDatabaseData()
        if (databaseData != null) {
            weatherDao.insert(databaseData)
        }
    }


}