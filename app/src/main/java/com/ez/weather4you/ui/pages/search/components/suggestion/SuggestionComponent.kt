package com.ez.weather4you.ui.pages.search.components.suggestion

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ListItem
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import com.ez.weather4you.R


@Composable
fun SuggestionComponent(
    modifier: Modifier,
    model: SuggestionComponentUIModel
) {
    if (model.suggestions.isNotEmpty()) {
        Column(modifier = modifier.fillMaxWidth()) {
            Text(
                modifier = Modifier.fillMaxWidth(),
                text = stringResource(R.string.suggestions),
                style = MaterialTheme.typography.titleSmall
            )
            LazyColumn(modifier = Modifier.fillMaxWidth()) {
                items(model.suggestions, key = { it.key }) { suggestion ->
                    ListItem(
                        headlineContent = { Text(suggestion.headline) },
                        supportingContent = { Text(suggestion.supportingContent) },
                        modifier = Modifier.clickable { model.onClick(suggestion.key) }
                    )
                }
            }
        }
    }
}

class SuggestionComponentParameterProvider : PreviewParameterProvider<SuggestionComponentUIModel> {
    private val twoItemList = listOf(
        SuggestionComponentUIModel.SuggestionUIModel(
            headline = "London",
            supportingContent = "City of London, Greater London, United Kingdom",
            key = 2412
        ),
        SuggestionComponentUIModel.SuggestionUIModel(
            headline = "Paris",
            supportingContent = "Ile-de-France, France",
            key = 41421
        )
    )

    private val suggestionSequence = sequenceOf(twoItemList, listOf())

    override val values: Sequence<SuggestionComponentUIModel> = suggestionSequence.map {
        SuggestionComponentUIModel(suggestions = it, onClick = {})
    }
}

@Preview(showBackground = true)
@Composable
fun SuggestionItemPreview(
    @PreviewParameter(SuggestionComponentParameterProvider::class) model: SuggestionComponentUIModel
) {
    SuggestionComponent(modifier = Modifier, model = model)
}
