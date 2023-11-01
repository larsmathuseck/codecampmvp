package de.comtec.codecamp.weathermvp.data.network

import de.comtec.codecamp.weathermvp.data.model.WeatherData
import retrofit2.Response
import retrofit2.http.GET




interface WeatherService {

    @GET("forecast?latitude=52.52&longitude=13.41&current=temperature,precipitation_probability")
    fun getWeather(): Response<WeatherData?>

}