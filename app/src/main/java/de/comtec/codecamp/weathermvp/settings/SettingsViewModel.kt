package de.comtec.codecamp.weathermvp.settings

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.work.ExistingPeriodicWorkPolicy
import androidx.work.PeriodicWorkRequest
import androidx.work.PeriodicWorkRequestBuilder
import androidx.work.WorkManager
import androidx.work.WorkRequest
import dagger.hilt.android.lifecycle.HiltViewModel
import de.comtec.codecamp.weathermvp.data.repositories.SettingsRepository
import de.comtec.codecamp.weathermvp.worker.WeatherWorker
import kotlinx.coroutines.launch
import java.util.concurrent.TimeUnit
import javax.inject.Inject

private const val WORK_KEY = "weather_work"

@HiltViewModel
class SettingsViewModel @Inject constructor(
    private val settingsRepository: SettingsRepository,
    private val workManger: WorkManager,
) : ViewModel() {

    val setting = settingsRepository.option1

    fun saveOption(option: Boolean) {
        viewModelScope.launch {
            settingsRepository.saveOption1(option)
            if (option) {
                startWork()
            } else {
                stopWork()
            }
        }
    }

    private fun startWork() {
        val weatherWorkRequest =
            PeriodicWorkRequestBuilder<WeatherWorker>(1, TimeUnit.HOURS).build()
        workManger.enqueueUniquePeriodicWork(
            WORK_KEY, ExistingPeriodicWorkPolicy.CANCEL_AND_REENQUEUE, weatherWorkRequest
        )
    }

    private fun stopWork() {
        workManger.cancelUniqueWork(WORK_KEY)
    }

}