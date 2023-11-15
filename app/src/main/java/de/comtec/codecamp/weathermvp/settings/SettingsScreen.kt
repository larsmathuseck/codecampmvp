package de.comtec.codecamp.weathermvp.settings

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel


@Preview
@Composable
fun SettingsScreen(viewModel: SettingsViewModel = hiltViewModel()) {
    val switchState = viewModel.setting.collectAsState(initial = false)
    SettingsSwitch(onCheckedChange = { viewModel.saveOption(it) }, checked = switchState.value)
}

@Composable
fun SettingsSwitch(checked: Boolean = false, onCheckedChange: (Boolean) -> Unit) {
    Row {
        Text(text = "Option 1")
        Spacer(modifier = Modifier.padding(8.dp))
        Switch(checked = checked, onCheckedChange = onCheckedChange)
    }
}