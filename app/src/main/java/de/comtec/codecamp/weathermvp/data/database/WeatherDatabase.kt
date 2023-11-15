package de.comtec.codecamp.weathermvp.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import de.comtec.codecamp.weathermvp.data.database.model.WeatherData

@Database(entities = [WeatherData::class], version = 1)
abstract class WeatherDatabase : RoomDatabase() {

    abstract fun weatherDao(): WeatherDao

}


