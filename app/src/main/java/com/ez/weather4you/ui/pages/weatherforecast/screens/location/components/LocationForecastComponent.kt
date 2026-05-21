package com.ez.weather4you.ui.pages.weatherforecast.screens.location.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Shadow
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import androidx.compose.ui.unit.dp
import com.ez.weather4you.domain.entity.forecast.Condition
import com.ez.weather4you.ui.pages.weatherforecast.screens.location.components.error.ErrorComponent
import com.ez.weather4you.ui.pages.weatherforecast.screens.location.components.error.ErrorComponentUIModelProvider
import com.ez.weather4you.ui.pages.weatherforecast.screens.location.components.forecasthour.WeatherForecastHourComponent
import com.ez.weather4you.ui.pages.weatherforecast.screens.location.components.forecasthour.WeatherForecastHourUIModelListProvider
import com.ez.weather4you.ui.pages.weatherforecast.screens.location.components.highlights.WeatherHighlightsComponent
import com.ez.weather4you.ui.pages.weatherforecast.screens.location.components.highlights.WeatherHighlightsUIModelProvider
import com.ez.weather4you.ui.pages.weatherforecast.resources.WeatherConditionResources

@Composable
fun LocationForecastComponent(padding: PaddingValues, model: LocationForecastComponentUIModel) {
    Box(modifier = Modifier.fillMaxSize()) {
        Image(
            painter = painterResource(model.backgroundImage),
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier.fillMaxSize()
        )
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
        ) {
            Text(
                modifier = Modifier
                    .padding(horizontal = 16.dp)
                    .fillMaxWidth(),
                text = model.location,
                style = MaterialTheme.typography.headlineLarge.copy(
                    shadow = Shadow(
                        color = MaterialTheme.colorScheme.primaryContainer.copy(
                            alpha = 0.5f
                        ), blurRadius = 1f
                    )
                ),
                color = MaterialTheme.colorScheme.onPrimaryContainer,
                textAlign = TextAlign.Center
            )

            model.highlights?.let { highlights ->
                WeatherHighlightsComponent(
                    modifier = Modifier.padding(horizontal = 16.dp),
                    model = highlights
                )
                Spacer(Modifier.height(8.dp))
            }

            model.error?.let { error ->
                ErrorComponent(
                    Modifier.padding(horizontal = 16.dp),
                    model = error
                )
                Spacer(Modifier.height(8.dp))
            }

            model.forecastHour?.let { forecastHour ->
                WeatherForecastHourComponent(
                    modifier = Modifier.padding(
                        horizontal = 16.dp
                    ), model = forecastHour
                )
            }
        }
    }
}


@Preview(showSystemUi = true, device = Devices.PIXEL_9)
@Composable
fun LocationForecastComponentPreview(@PreviewParameter(provider = LocationForecastComponentUIModelProvider::class) model: LocationForecastComponentUIModel) {
    LocationForecastComponent(padding = PaddingValues(top = 30.dp), model = model)
}

class LocationForecastComponentUIModelProvider :
    PreviewParameterProvider<LocationForecastComponentUIModel> {

    override val values: Sequence<LocationForecastComponentUIModel> = sequenceOf(
        Condition.DAY_SUNNY,
        Condition.NIGHT_CLEAR
    ).map { WeatherConditionResources.associatedWith(it) }.map {
        it.let {
            LocationForecastComponentUIModel(
                location = "London",
                backgroundImage = it.backgroundImage,
                colorScheme = it.colorScheme,
                highlights = WeatherHighlightsUIModelProvider().values.first(),
                forecastHour = WeatherForecastHourUIModelListProvider().values.first(),
                error = ErrorComponentUIModelProvider().values.first()
            )
        }
    }
}
