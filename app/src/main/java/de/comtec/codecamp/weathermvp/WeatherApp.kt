package de.comtec.codecamp.weathermvp

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.WindowInsetsSides
import androidx.compose.foundation.layout.consumeWindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.only
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawing
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavDestination
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import de.comtec.codecamp.weathermvp.navigation.WeatherNavHost
import de.comtec.codecamp.weathermvp.navigation.WeatherScreen


@OptIn(ExperimentalLayoutApi::class)
@Composable
@Preview
fun WeatherApp() {

    val navController = rememberNavController()

    val currentDestination = navController.currentBackStackEntryAsState().value?.destination

    val currentWeatherDestination = WeatherScreen.valueOf(
        currentDestination?.route ?: WeatherScreen.Home.name
    )

    Scaffold(
        bottomBar = {
            WeatherBottomBar(
                destinations = WeatherScreen.values().asList(), onNavigateToDestination = {
                    navController.navigate(
                        it.name
                    )
                }, currentDestination = currentDestination, modifier = Modifier
            )
        },
    ) { innerPadding ->
        Row(
            Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .consumeWindowInsets(innerPadding)
                .windowInsetsPadding(
                    WindowInsets.safeDrawing.only(
                        WindowInsetsSides.Horizontal
                    )
                )
        ) {
            Column(Modifier.fillMaxSize()) {
                WeatherAppBar(
                    currentScreen = currentWeatherDestination,
                    canNavigateBack = false,
                    navigateUp = { navController.navigateUp() },
                )
                WeatherNavHost(
                    startDestination = WeatherScreen.Home.name,
                    navController = navController,
                )
            }
        }
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun WeatherAppBar(
    currentScreen: WeatherScreen?,
    canNavigateBack: Boolean,
    navigateUp: () -> Unit,
) {
    TopAppBar(
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = MaterialTheme.colorScheme.primaryContainer,
            titleContentColor = MaterialTheme.colorScheme.primary,
        ),
        title = {
            Text(stringResource((currentScreen ?: WeatherScreen.Home).title))
    }, navigationIcon = {
        if (canNavigateBack) {
            IconButton(onClick = navigateUp) {
                Icon(
                    imageVector = Icons.Filled.ArrowBack, contentDescription = null
                )
            }
        }
    })
}


@Composable
private fun WeatherBottomBar(
    destinations: List<WeatherScreen>,
    onNavigateToDestination: (WeatherScreen) -> Unit,
    currentDestination: NavDestination?,
    modifier: Modifier = Modifier
) {
    NavigationBar(
        modifier = modifier
    ) {
        destinations.forEach { destination ->
            val selected = currentDestination.isTopLevelDestinationInHierarchy(destination.name)
            NavigationBarItem(selected = selected,
                onClick = { onNavigateToDestination(destination) },
                icon = {
                    val iconResId = if (selected) {
                        destination.selectedIcon
                    } else {
                        destination.unselectedIcon
                    }
                    Icon(
                        painterResource(id = iconResId), contentDescription = null
                    )
                },
                label = { Text(stringResource(destination.title)) })
        }
    }
}

private fun NavDestination?.isTopLevelDestinationInHierarchy(destinationName: String) =
    this?.hierarchy?.any {
        it.route?.contains(destinationName, true) ?: false
    } ?: false
