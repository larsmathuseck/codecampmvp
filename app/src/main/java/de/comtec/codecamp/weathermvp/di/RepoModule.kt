package de.comtec.codecamp.weathermvp.di

import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import de.comtec.codecamp.weathermvp.data.model.WeatherData
import de.comtec.codecamp.weathermvp.data.network.QuotesService
import de.comtec.codecamp.weathermvp.data.network.WeatherService
import de.comtec.codecamp.weathermvp.data.repositories.QuotesRepository
import de.comtec.codecamp.weathermvp.data.repositories.WeatherRepository
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory


@Module
@InstallIn(SingletonComponent::class)
object RepoModule {

    data class RetroFitHolder(val weatherRetrofit: Retrofit, val quotesRetrofit: Retrofit)

    @Provides
    fun providesRetroFit(): RetroFitHolder {

        val weatherRetrofit =
            Retrofit.Builder().baseUrl("https://api.open-meteo.com/v1/")
                .addConverterFactory(
                    MoshiConverterFactory.create(
                        Moshi.Builder().add(KotlinJsonAdapterFactory()).build()
                    )
                ).build()

        val quotesRetrofit = Retrofit.Builder().baseUrl("https://api.quotable.io/")
            .addConverterFactory(MoshiConverterFactory.create()).build()

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