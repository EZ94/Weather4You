package com.ez.weather4you.ui.pages.search.components.search

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.tooling.preview.PreviewParameterProvider

@Composable
fun SearchBox(modifier: Modifier, model: SearchBoxUIModel) {
    OutlinedTextField(
        value = model.value,
        onValueChange = model.onValueChange,
        modifier = modifier
            .fillMaxWidth(),
        placeholder = { Text("Search location...") },
        singleLine = true
    )
}

class SearchBoxUIModelPreviewParameterProvider : PreviewParameterProvider<SearchBoxUIModel> {
    override val values: Sequence<SearchBoxUIModel> = sequenceOf(
        "Lon",
        ""
    ).map { SearchBoxUIModel(it, {}) }
}


@Preview(showBackground = true)
@Composable
fun SearchBoxPreview(@PreviewParameter(SearchBoxUIModelPreviewParameterProvider::class) model: SearchBoxUIModel) {
    SearchBox(modifier = Modifier, model = model)
}
