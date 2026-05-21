package com.ez.weather4you.ui.pages.search.components.locations

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ListItem
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SwipeToDismissBox
import androidx.compose.material3.SwipeToDismissBoxState
import androidx.compose.material3.SwipeToDismissBoxValue
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import androidx.compose.ui.unit.dp
import com.ez.weather4you.R


@Composable
fun LocationsComponent(
    modifier: Modifier,
    model: LocationComponentUIModel
) {
    model.currentLocation?.let {
        Text(
            text = stringResource(R.string.current_location),
            style = MaterialTheme.typography.titleSmall
        )
        LocationItem(it)
    }
    if (model.savedLocations.isNotEmpty()) {
        Column(modifier = modifier.fillMaxWidth()) {
            Text(
                text = stringResource(R.string.saved_locations),
                style = MaterialTheme.typography.titleSmall
            )
            LazyColumn {
                items(
                    model.savedLocations,
                    key = { it.key }) { savedLocation ->

                    SwipeToDismissBox(
                        state = SwipeToDismissBoxState(
                            initialValue = SwipeToDismissBoxValue.Settled,
                            positionalThreshold = { it * 0.5f }
                        ),
                        backgroundContent = {
                            Row(
                                modifier = Modifier
                                    .fillMaxSize()
                                    .padding(horizontal = 20.dp),
                                verticalAlignment = Alignment.CenterVertically,
                                horizontalArrangement = Arrangement.End
                            ) {
                                Text("Delete", color = Color.Red)
                            }
                        },
                        modifier = Modifier.fillMaxWidth(),
                        enableDismissFromStartToEnd = false,
                        enableDismissFromEndToStart = true,
                        gesturesEnabled = true,
                        onDismiss = { model.onSwipe(savedLocation.key) }
                    ) {

                        LocationItem(savedLocation)
                    }


                }
            }
        }
    }
}

@Composable
fun LocationItem(savedLocation: LocationComponentUIModel.LocationUIModel) {
    ListItem(
        headlineContent = { Text(savedLocation.displayName) },
        trailingContent = {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Text(
                    text = savedLocation.temperature,
                    style = MaterialTheme.typography.titleLarge
                )
                Image(
                    painter = painterResource(savedLocation.icon),
                    contentDescription = stringResource(savedLocation.iconDescription),
                    modifier = Modifier.size(40.dp)
                )
            }
        }
    )
}

class LocationsComponentUIModelProvider : PreviewParameterProvider<LocationComponentUIModel> {

    private val twoItemList =
        listOf(
            LocationComponentUIModel.LocationUIModel(
                displayName = "London, United Kingdom",
                key = 1234,
                temperature = "15°",
                icon = R.drawable.day_113,
                iconDescription = R.string.day_1006
            ),
            LocationComponentUIModel.LocationUIModel(
                displayName = "Rome, Italy",
                key = 214124,
                temperature = "22°",
                icon = R.drawable.day_113,
                iconDescription = R.string.day_1000
            )
        )

    private val savedLocationsSequence = sequenceOf(twoItemList, listOf())

    private val oneItemList = twoItemList.first()

    private val currentLocationSequence = sequenceOf(oneItemList, null)

    override val values: Sequence<LocationComponentUIModel>
        get() = savedLocationsSequence.zip(currentLocationSequence) { saved, current ->
            LocationComponentUIModel(current, saved , {})
        }

}


@Preview(showBackground = true)
@Composable
fun LocationItemPreview(
    @PreviewParameter(LocationsComponentUIModelProvider::class) model: LocationComponentUIModel
) {
    LocationsComponent(modifier = Modifier.fillMaxWidth(), model = model)
}


