package com.ez.weather4you.ui.pages.search.components.suggestion

import com.ez.weather4you.domain.entity.forecast.Location
import com.ez.weather4you.ui.pages.search.components.search.SearchBoxUIModel
import com.ez.weather4you.ui.pages.search.components.suggestion.SuggestionComponentUIModel.SuggestionUIModel

data class SuggestionComponentUIModel(
    val suggestions: List<SuggestionUIModel>,
    val onClick: (Int) -> Unit
) {
    data class SuggestionUIModel(
        val headline: String,
        val supportingContent: String,
        val key: Int
    )

    companion object {
        fun getDefault() = SuggestionComponentUIModel(emptyList(), {})
    }

}

fun Location.toSuggestionUIModel() = SuggestionUIModel(
    headline = name,
    supportingContent = if (!region.isNullOrEmpty()) "$region, $country" else country,
    key = id
)

fun List<Location>.toSuggestionUIModel() = map { it.toSuggestionUIModel() }