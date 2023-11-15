package de.comtec.codecamp.weathermvp.data.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import de.comtec.codecamp.weathermvp.data.database.model.WeatherData
import kotlinx.coroutines.flow.Flow

@Dao
interface WeatherDao {
    @Query("SELECT * FROM weatherdata")
    fun getWeather(): Flow<WeatherData>

    @Insert
    suspend fun insert(weather: WeatherData)

}