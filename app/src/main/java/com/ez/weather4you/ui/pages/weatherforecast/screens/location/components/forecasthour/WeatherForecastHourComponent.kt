package com.ez.weather4you.ui.pages.weatherforecast.screens.location.components.forecasthour

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material3.Card
import androidx.compose.material3.CardColors
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.paint
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import androidx.compose.ui.unit.dp
import com.ez.weather4you.R
import com.ez.weather4you.domain.entity.forecast.Condition
import com.ez.weather4you.ui.pages.weatherforecast.resources.WeatherConditionResources
import com.ez.weather4you.ui.theme.Weather4YouTheme

@Composable
fun WeatherForecastHourComponent(model: List<WeatherForecastHourUIModel>, modifier: Modifier) {
    Card(
        modifier = modifier,
        colors = CardColors(
            containerColor = MaterialTheme.colorScheme.primaryContainer.copy(alpha = 0.5f),
            contentColor = MaterialTheme.colorScheme.onPrimaryContainer,
            disabledContainerColor = MaterialTheme.colorScheme.primaryContainer.copy(alpha = 0.5f),
            disabledContentColor = MaterialTheme.colorScheme.onPrimaryContainer
        )
    ) {
        Column(
            Modifier
                .fillMaxWidth()
                .padding(8.dp)
        ) {
            Text(
                modifier = Modifier.fillMaxWidth(),
                text = stringResource(R.string.hour_forecast_title),
                style = MaterialTheme.typography.titleSmall
            )
            Spacer(
                Modifier
                    .fillMaxWidth()
                    .padding(vertical = 2.dp)
                    .height(1.dp)
                    .background(MaterialTheme.colorScheme.onPrimaryContainer)
            )
            Row(
                Modifier
                    .fillMaxWidth()
                    .horizontalScroll(enabled = true, state = rememberScrollState())
            ) {
                model.forEach {
                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
                        Text(text = it.time, style = MaterialTheme.typography.labelMedium)
                        Image(
                            modifier = Modifier.size(48.dp),
                            painter = painterResource(it.icon),
                            contentDescription = stringResource(it.weatherDescription)
                        )
                        Text(text = it.temperature, style = MaterialTheme.typography.labelLarge)
                    }
                }
            }
        }
    }


}

@Composable
@Preview(showSystemUi = true, device = Devices.PIXEL_9)
fun WeatherForecastHourComponentPreview(
    @PreviewParameter(
        WeatherForecastHourUIModelListProvider::class
    ) model: List<WeatherForecastHourUIModel>
) {
    val weatherResources = WeatherConditionResources.associatedWith(Condition.DAY_SUNNY)
    Weather4YouTheme(colorScheme = weatherResources.colorScheme) {
        Scaffold(Modifier.fillMaxSize()) { padding ->
            Box(
                Modifier
                    .fillMaxSize()
                    .paint(
                        painterResource(weatherResources.backgroundImage),
                        contentScale = ContentScale.Crop
                    )
                    .padding(padding)
            )
            { WeatherForecastHourComponent(model, Modifier.padding(16.dp)) }
        }
    }
}

class WeatherForecastHourUIModelListProvider :
    PreviewParameterProvider<List<WeatherForecastHourUIModel>> {

    override val values: Sequence<List<WeatherForecastHourUIModel>> = listOf(
        (0..23).map { hour ->
            WeatherForecastHourUIModel(
                time = "${hour}:00",
                icon = R.drawable.night_182,
                temperature = "22\u00b0",
                weatherDescription = R.string.day_1006
            )
        }
    ).asSequence()
}
