package de.comtec.codecamp.weathermvp.settings

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import de.comtec.codecamp.weathermvp.data.repositories.SettingsRepository
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SettingsViewModel @Inject constructor(
    private val settingsRepository: SettingsRepository
) : ViewModel() {

    val setting = settingsRepository.option1

    fun saveOption(option: Boolean) {
        viewModelScope.launch {
            settingsRepository.saveOption1(option)
        }
    }

}