package de.comtec.codecamp.weathermvp.data.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import de.comtec.codecamp.weathermvp.data.database.model.WeatherData

@Dao
interface WeatherDao {
    @Query("SELECT * FROM weatherdata")
    fun getWeather(): WeatherData

    @Insert
    fun insert(weather: WeatherData)

}