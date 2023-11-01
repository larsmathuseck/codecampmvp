package de.comtec.codecamp.weathermvp.data.model

import com.squareup.moshi.Json

data class Quote(
    @Json(name = "content") val content: String,
    @Json(name = "author") val author: String,
)