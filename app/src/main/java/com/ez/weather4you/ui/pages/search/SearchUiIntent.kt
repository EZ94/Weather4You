package com.ez.weather4you.ui.pages.search

sealed interface SearchUiIntent {
    data class QueryChange(val newQuery: String) : SearchUiIntent
    data class AddLocation(val locationId: Int) : SearchUiIntent
    data class DeleteLocation(val id: Int) : SearchUiIntent
}
