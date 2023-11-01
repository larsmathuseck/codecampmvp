package de.comtec.codecamp.weathermvp.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import de.comtec.codecamp.weathermvp.data.network.WeatherService
import de.comtec.codecamp.weathermvp.data.repositories.WeatherRepository
import retrofit2.Retrofit


@Module
@InstallIn(SingletonComponent::class)
object RepoModule {

    @Provides
    fun providesRetroFit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl("https://api.open-meteo.com/v1/")
            .build()
    }

    @Provides
    fun provideAPI(retrofit: Retrofit): WeatherService {
        return retrofit.create(WeatherService::class.java)
    }

    @Provides
    fun provideWeatherRepo(api: WeatherService): WeatherRepository {
        return WeatherRepository(api)
    }

}