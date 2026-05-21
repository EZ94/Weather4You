package com.ez.weather4you.ui.pages.weatherforecast.screens.location

import android.util.Log
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.onVisibilityChanged
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import com.ez.weather4you.R
import com.ez.weather4you.ui.pages.weatherforecast.screens.location.components.LocationForecastComponent
import com.ez.weather4you.ui.pages.weatherforecast.screens.location.components.LocationForecastComponentUIModelProvider
import com.ez.weather4you.ui.theme.Weather4YouTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LocationsForecastScreen(model: LocationsForecastScreenUIModel) {
    val pagerState = rememberPagerState { model.locations.size }
    val location = model.locations.getOrNull(pagerState.currentPage)

    Weather4YouTheme(colorScheme = location?.colorScheme ?: MaterialTheme.colorScheme) {
        Scaffold(
            modifier = Modifier.fillMaxSize().onVisibilityChanged { model.onVisibilityChange(it) },
            topBar = {
                TopAppBar(
                    title = {},
                    actions = {
                        IconButton(onClick = model.onSettingsClick) {
                            Icon(
                                painter = painterResource(R.drawable.settings),
                                contentDescription = stringResource(R.string.settings),
                                tint = MaterialTheme.colorScheme.onPrimaryContainer
                            )
                        }
                    },
                    colors = TopAppBarDefaults.topAppBarColors(
                        containerColor = Color.Transparent
                    )
                )
            },
            floatingActionButton = {
                FloatingActionButton(
                    onClick = model.onFabClick,
                    containerColor = MaterialTheme.colorScheme.primaryContainer,
                    contentColor = MaterialTheme.colorScheme.onPrimaryContainer
                ) {
                    Icon(
                        painterResource(R.drawable.list),
                        contentDescription = stringResource(R.string.list)
                    )
                }
            }
        ) { padding ->
            HorizontalPager(
                state = pagerState,
                modifier = Modifier.fillMaxSize()
            ) { page ->
                val pageModel = model.locations[page]
                LocationForecastComponent(padding = padding, model = pageModel)
            }
        }
    }
}

class LocationsForecastScreenUIModelProvider :
    PreviewParameterProvider<LocationsForecastScreenUIModel> {

    private val forecast = LocationForecastComponentUIModelProvider().values
    override val values: Sequence<LocationsForecastScreenUIModel>
        get() = forecast.map { LocationsForecastScreenUIModel(locations = listOf(it), onFabClick = {}, onSettingsClick = {}, onVisibilityChange = {}) }
}

@Preview(showSystemUi = true, device = Devices.PIXEL_9)
@Composable
fun LocationsForecastScreenPreview(
    @PreviewParameter(LocationsForecastScreenUIModelProvider::class)
    model: LocationsForecastScreenUIModel
) {
    LocationsForecastScreen(model)
}
