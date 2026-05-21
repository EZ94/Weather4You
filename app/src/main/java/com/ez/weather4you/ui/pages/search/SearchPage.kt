package com.ez.weather4you.ui.pages.search

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Devices.PIXEL_9
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import androidx.compose.ui.unit.dp
import com.ez.weather4you.R
import com.ez.weather4you.ui.pages.search.components.locations.LocationsComponent
import com.ez.weather4you.ui.pages.search.components.locations.LocationsComponentUIModelProvider
import com.ez.weather4you.ui.pages.search.components.search.SearchBox
import com.ez.weather4you.ui.pages.search.components.search.SearchBoxUIModelPreviewParameterProvider
import com.ez.weather4you.ui.pages.search.components.suggestion.SuggestionComponent
import com.ez.weather4you.ui.pages.search.components.suggestion.SuggestionComponentParameterProvider

@Composable
fun SearchPage(
    model: SearchPageUIModel
) {
    Column(
        modifier = Modifier.fillMaxWidth().fillMaxHeight(0.9f).padding(16.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = stringResource(R.string.manage_locations),
                style = MaterialTheme.typography.titleLarge
            )
        }

        SearchBox(
            modifier = Modifier,
            model = model.searchBoxUIModel
        )

        SuggestionComponent(
            Modifier.padding(horizontal = 16.dp),
            model = model.suggestionComponentUIModel
        )

        if (model.suggestionComponentUIModel.suggestions.isNotEmpty() && model.locationsComponentUIModel.savedLocations.isNotEmpty()) HorizontalDivider(
            modifier = Modifier.size(height = 8.dp, width = 0.dp)
        )

        LocationsComponent(
            modifier = Modifier.padding(horizontal = 16.dp),
            model = model.locationsComponentUIModel
        )
    }
}

class SearchUIStateParameterProvider : PreviewParameterProvider<SearchPageUIModel> {

    private val searchBoxValues = SearchBoxUIModelPreviewParameterProvider().values
    private val suggestionsValues = SuggestionComponentParameterProvider().values
    private val locationValues = LocationsComponentUIModelProvider().values
    override val values: Sequence<SearchPageUIModel> =
        searchBoxValues.zip(suggestionsValues)
            .zip(locationValues) { (search, suggestions), locations ->
                SearchPageUIModel(search, locations, suggestions)
            }
}

@Preview(showBackground = true, device = PIXEL_9)
@Composable
fun SearchPagePreview(
    @PreviewParameter(SearchUIStateParameterProvider::class) model: SearchPageUIModel
) {
    SearchPage(
        model = model
    )
}
