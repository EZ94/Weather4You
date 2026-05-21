package com.ez.weather4you.ui.pages.search.components.search

data class SearchBoxUIModel(val value: String, val onValueChange: (String) -> Unit) {
    companion object {
        fun getDefault() = SearchBoxUIModel("", {})
    }
}

