package com.ez.weather4you.ui.pages.weatherforecast.screens.location.components.highlights

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
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
fun WeatherHighlightsComponent(model: WeatherHighlightsUIModel, modifier: Modifier) {
    Column(
        modifier
            .fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Image(
                painter = painterResource(model.icon),
                contentDescription = stringResource(model.weatherDescription)
            )
            Text(
                text = model.temperature,
                style = MaterialTheme.typography.headlineLarge,
                color = MaterialTheme.colorScheme.onPrimaryContainer
            )
        }
        Text(
            text = String.format(stringResource(R.string.feels_like_string_param), model.feelsLike),
            style = MaterialTheme.typography.titleMedium,
            color = MaterialTheme.colorScheme.onPrimaryContainer
        )
    }
}

@Composable
@Preview(showSystemUi = true, device = Devices.PIXEL_9)
fun WeatherHighlightsComponentPreview(
    @PreviewParameter(
        WeatherHighlightsUIModelProvider::class
    ) model: WeatherHighlightsUIModel
) {
    val weatherResources = WeatherConditionResources.associatedWith(Condition.NIGHT_CLEAR)
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
            { WeatherHighlightsComponent(model, Modifier.padding(16.dp)) }
        }
    }
}

class WeatherHighlightsUIModelProvider :
    PreviewParameterProvider<WeatherHighlightsUIModel> {
    override val values: Sequence<WeatherHighlightsUIModel> = listOf(
        WeatherHighlightsUIModel(
            icon = R.drawable.day_113,
            weatherDescription = R.string.day_1006,
            temperature = "9\u00b0",
            feelsLike = "6\u00b0"
        )
    ).asSequence()
}
