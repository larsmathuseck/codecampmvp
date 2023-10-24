package de.comtec.codecamp.weathermvp.navigation

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import de.comtec.codecamp.weathermvp.R

enum class WeatherScreen(
    @StringRes val title: Int,
    @DrawableRes val selectedIcon: Int,
    @DrawableRes val unselectedIcon: Int
) {
    Home(
        title = R.string.home_screen_name,
        selectedIcon = R.drawable.ic_home,
        unselectedIcon = R.drawable.ic_home
    ),
    Settings(
        title = R.string.settings_screen_name,
        selectedIcon = R.drawable.ic_settings,
        unselectedIcon = R.drawable.ic_settings
    );
}
