package de.comtec.codecamp.weathermvp.data.network

import de.comtec.codecamp.weathermvp.data.model.Quote
import de.comtec.codecamp.weathermvp.data.model.WeatherData
import retrofit2.Response
import retrofit2.http.GET




interface QuotesService {

    @GET("random")
    suspend fun getRandomQuote(): Response<Quote?>

}