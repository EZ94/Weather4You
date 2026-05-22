package com.ez.weather4you.ui.pages.search

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ez.weather4you.domain.repository.SearchRepository
import com.ez.weather4you.domain.repository.WeatherForecastRepository
import com.ez.weather4you.domain.usecase.FormatUnitUseCase
import com.ez.weather4you.ui.pages.search.components.locations.toLocationComponentUIModel
import com.ez.weather4you.ui.pages.search.components.search.SearchBoxUIModel
import com.ez.weather4you.ui.pages.search.components.suggestion.SuggestionComponentUIModel
import com.ez.weather4you.ui.pages.search.components.suggestion.toSuggestionUIModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject


@OptIn(FlowPreview::class)
@HiltViewModel
class SearchViewModel @Inject constructor(
    private val searchRepository: SearchRepository,
    private val weatherForecastRepository: WeatherForecastRepository,
    private val formatUnitUseCase: FormatUnitUseCase
) : ViewModel() {

    private val _query = MutableStateFlow("")

    init {
        viewModelScope.launch {
            _query
                .debounce(300)
                .filter { it.isNotEmpty() }
                .distinctUntilChanged()
                .collect { searchRepository.search(it) }
        }
    }

    val uiState: StateFlow<SearchUiState> = combine(
        _query,
        weatherForecastRepository.weatherForecasts,
        searchRepository.suggestions
    ) { query, savedForecasts, suggestions ->

        val filteredSuggestions = suggestions
            .filter { suggestion ->
                savedForecasts.none { it.location.id == suggestion.id } &&
                        query.isNotEmpty()
            }
            .take(5).toSuggestionUIModel()
        val suggestionComponentUIModel = SuggestionComponentUIModel(
            suggestions = filteredSuggestions,
            onClick = { onIntent(SearchUiIntent.AddLocation(it)) }
        )

        val locationsComponentUIModel = savedForecasts.toLocationComponentUIModel(
            formatUnitUseCase = formatUnitUseCase,
            filterLocationName = {
                it.startsWith(query, ignoreCase = true)
            },
            onSwipe = { onIntent(SearchUiIntent.DeleteLocation(it)) }
        )

        val searchBoxUIModel = SearchBoxUIModel(query, { onIntent(SearchUiIntent.QueryChange(it)) })

        SearchUiState(
            searchBoxUIModel = searchBoxUIModel,
            suggestionComponentUIModel = suggestionComponentUIModel,
            locationsComponentUIModel = locationsComponentUIModel
        )
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5000),
        initialValue = SearchUiState.getDefault()
    )

    fun onIntent(intent: SearchUiIntent) {
        when (intent) {
            is SearchUiIntent.QueryChange -> onQueryChange(intent.newQuery)
            is SearchUiIntent.AddLocation -> addLocation(intent.locationId)
            is SearchUiIntent.DeleteLocation -> deleteLocation(intent.id)
        }
    }

    private fun onQueryChange(newQuery: String) {
        _query.value = newQuery
    }

    private fun addLocation(locationId: Int) {
        viewModelScope.launch {
            weatherForecastRepository.addLocation(locationId)
            _query.value = ""
        }
    }

    private fun deleteLocation(id: Int) {
        viewModelScope.launch {
            weatherForecastRepository.deleteSavedLocation(id)
        }
    }
}
