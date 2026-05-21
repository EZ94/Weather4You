package com.ez.weather4you.ui.pages.search

import com.ez.weather4you.ui.pages.search.components.locations.LocationComponentUIModel
import com.ez.weather4you.ui.pages.search.components.search.SearchBoxUIModel
import com.ez.weather4you.ui.pages.search.components.suggestion.SuggestionComponentUIModel

data class SearchPageUIModel(
    val searchBoxUIModel: SearchBoxUIModel,
    val locationsComponentUIModel: LocationComponentUIModel,
    val suggestionComponentUIModel: SuggestionComponentUIModel
) {
    companion object {
        fun getDefault() = SearchPageUIModel(
            SearchBoxUIModel.getDefault(),
            LocationComponentUIModel.getDefault(),
            SuggestionComponentUIModel.getDefault()
        )
    }
}