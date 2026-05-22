package com.ez.weather4you.ui.pages.settings

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SegmentedButton
import androidx.compose.material3.SegmentedButtonDefaults
import androidx.compose.material3.SingleChoiceSegmentedButtonRow
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import com.ez.weather4you.R
import com.ez.weather4you.domain.entity.HourUnit
import com.ez.weather4you.domain.entity.TemperatureUnit

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SettingsPage(
    onBack: () -> Unit,
    viewModel: SettingsViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState.collectAsState()

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(stringResource(R.string.settings)) },
                navigationIcon = {
                    IconButton(onClick = onBack) {
                        Icon(
                            painter = painterResource(R.drawable.outline_arrow_back_24),
                            contentDescription = stringResource(R.string.back)
                        )
                    }
                }
            )
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .padding(16.dp)
        ) {
            Text(
                text = stringResource(R.string.temperature_unit),
                style = MaterialTheme.typography.titleMedium
            )
            Spacer(modifier = Modifier.height(8.dp))
            TemperatureUnitSegmentedButton(
                selectedUnit = uiState.preferences?.temperatureUnit ?: TemperatureUnit.CELSIUS,
                onUnitSelected = { viewModel.onIntent(SettingsUiIntent.UpdateTemperatureUnit(it)) }
            )

            Spacer(modifier = Modifier.height(24.dp))

            Text(
                text = stringResource(R.string.hour_unit),
                style = MaterialTheme.typography.titleMedium
            )
            Spacer(modifier = Modifier.height(8.dp))
            HourUnitSegmentedButton(
                selectedUnit = uiState.preferences?.hourUnit ?: HourUnit.H24,
                onUnitSelected = { viewModel.onIntent(SettingsUiIntent.UpdateHourUnit(it)) }
            )
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TemperatureUnitSegmentedButton(
    selectedUnit: TemperatureUnit,
    onUnitSelected: (TemperatureUnit) -> Unit
) {
    val options = listOf(TemperatureUnit.CELSIUS, TemperatureUnit.FAHRENHEIT)
    SingleChoiceSegmentedButtonRow(modifier = Modifier.fillMaxWidth()) {
        options.forEachIndexed { index, unit ->
            SegmentedButton(
                selected = unit == selectedUnit,
                onClick = { onUnitSelected(unit) },
                shape = SegmentedButtonDefaults.itemShape(index = index, count = options.size)
            ) {
                Text(
                    text = when (unit) {
                        TemperatureUnit.CELSIUS -> stringResource(R.string.celsius)
                        TemperatureUnit.FAHRENHEIT -> stringResource(R.string.fahrenheit)
                    }
                )
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HourUnitSegmentedButton(
    selectedUnit: HourUnit,
    onUnitSelected: (HourUnit) -> Unit
) {
    val options = listOf(HourUnit.H12, HourUnit.H24)
    SingleChoiceSegmentedButtonRow(modifier = Modifier.fillMaxWidth()) {
        options.forEachIndexed { index, unit ->
            SegmentedButton(
                selected = unit == selectedUnit,
                onClick = { onUnitSelected(unit) },
                shape = SegmentedButtonDefaults.itemShape(index = index, count = options.size)
            ) {
                Text(
                    text = when (unit) {
                        HourUnit.H12 -> stringResource(R.string.am_pm)
                        HourUnit.H24 -> stringResource(R.string.h24)
                    }
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun SettingsPagePreview() {
    MaterialTheme {
        SettingsPage(onBack = {})
    }
}
