package de.comtec.codecamp.weathermvp.data.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

data class WeatherData(
    @Json(name = "current") val current: Current
)

@JsonClass(generateAdapter = true)
data class Current(
    @Json(name = "time") val time: String,
    @Json(name = "temperature") val temperature: Double,
    @Json(name = "precipitation_probability") val precipitationProbability: Double
)
