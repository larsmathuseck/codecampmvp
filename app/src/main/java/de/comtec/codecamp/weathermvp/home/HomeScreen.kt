package de.comtec.codecamp.weathermvp.home

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel


@Preview
@Composable
fun HomeScreen(viewModel: HomeViewModel = hiltViewModel()) {

    val weather = viewModel.weatherData.value

    val temp = weather?.current?.temperature

}
