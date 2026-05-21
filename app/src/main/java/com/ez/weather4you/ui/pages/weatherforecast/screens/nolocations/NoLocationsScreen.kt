package com.ez.weather4you.ui.pages.weatherforecast.screens.nolocations

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import com.ez.weather4you.R

@Composable
fun NoLocationsScreen(model: NoLocationsScreenUIModel) {
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceAround
    ) {
        Text(text = stringResource(model.message), textAlign = TextAlign.Center)
        Button(onClick = model.onClick) {
            Text(text = stringResource(R.string.location_permission_button_search))
        }
    }
}

class NoLocationsScreenUIModelProvider : PreviewParameterProvider<NoLocationsScreenUIModel> {
    override val values: Sequence<NoLocationsScreenUIModel> = sequenceOf(
        NoLocationsScreenUIModel(R.string.out_of_date_error) {},
        NoLocationsScreenUIModel(R.string.out_of_date_refreshing) {}
    )
}

@Preview(showSystemUi = true, device = Devices.PIXEL_9)
@Composable
fun NoLocationScreenPreview(@PreviewParameter(NoLocationsScreenUIModelProvider::class) model: NoLocationsScreenUIModel) {
    NoLocationsScreen(model)
}