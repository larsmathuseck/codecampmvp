package de.comtec.codecamp.weathermvp.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import de.comtec.codecamp.weathermvp.data.network.QuotesService
import de.comtec.codecamp.weathermvp.data.network.WeatherService
import de.comtec.codecamp.weathermvp.data.repositories.QuotesRepository
import de.comtec.codecamp.weathermvp.data.repositories.WeatherRepository
import retrofit2.Retrofit


@Module
@InstallIn(SingletonComponent::class)
object RepoModule {

    data class RetroFitHolder(val weatherRetrofit: Retrofit, val quotesRetrofit: Retrofit)

    @Provides
    fun providesRetroFit(): RetroFitHolder {
        val weatherRetrofit = Retrofit.Builder()
            .baseUrl("https://api.open-meteo.com/v1/")
            .build()

        val quotesRetrofit = Retrofit.Builder()
            .baseUrl("https://api.quotable.io/")
            .build()

        return RetroFitHolder(weatherRetrofit, quotesRetrofit)
    }

    @Provides
    fun provideWeatherAPI(retroFitHolder: RetroFitHolder): WeatherService {
        return retroFitHolder.weatherRetrofit.create(WeatherService::class.java)
    }

    @Provides
    fun provideQuotesAPI(retroFitHolder: RetroFitHolder): QuotesService {
        return retroFitHolder.quotesRetrofit.create(QuotesService::class.java)
    }

    @Provides
    fun provideWeatherRepo(api: WeatherService): WeatherRepository {
        return WeatherRepository(api)
    }

    @Provides
    fun provideQuotesRepo(api: QuotesService): QuotesRepository {
        return QuotesRepository(api)
    }

}