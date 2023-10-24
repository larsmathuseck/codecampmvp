package de.comtec.codecamp.weathermvp.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import de.comtec.codecamp.weathermvp.home.HomeScreen
import de.comtec.codecamp.weathermvp.settings.SettingsScreen

@Composable
fun WeatherNavHost(
    modifier: Modifier = Modifier,
    navController: NavHostController,
    startDestination: String = WeatherScreen.Home.name,
) {
    NavHost(
        navController = navController,
        startDestination = startDestination,
        modifier = modifier,
    ) {
        composable(route = WeatherScreen.Home.name) {
            HomeScreen()
        }
        composable(route = WeatherScreen.Settings.name) {
            SettingsScreen()
        }
    }

}

