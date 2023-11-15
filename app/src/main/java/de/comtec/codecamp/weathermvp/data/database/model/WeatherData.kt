package de.comtec.codecamp.weathermvp.data.database.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class WeatherData(
    @PrimaryKey val uid: Int = 1,
    @ColumnInfo(name = "temperature") val temperature: Double?,
    @ColumnInfo(name = "precipitation_probability") val precipitationProbability: Double?,
)