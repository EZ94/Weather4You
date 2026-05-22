package com.ez.weather4you.ui.navigation

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSerializable
import androidx.compose.ui.Modifier
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.lifecycle.viewmodel.navigation3.rememberViewModelStoreNavEntryDecorator
import androidx.navigation3.runtime.NavBackStack
import androidx.navigation3.runtime.entryProvider
import androidx.navigation3.runtime.rememberSaveableStateHolderNavEntryDecorator
import androidx.navigation3.ui.NavDisplay
import com.ez.weather4you.ui.navigation.bottomsheet.BottomSheetSceneStrategy
import com.ez.weather4you.ui.pages.onboarding.OnboardingPage
import com.ez.weather4you.ui.pages.onboarding.OnboardingViewModel
import com.ez.weather4you.ui.pages.search.SearchPage
import com.ez.weather4you.ui.pages.search.SearchViewModel
import com.ez.weather4you.ui.pages.settings.SettingsPage
import com.ez.weather4you.ui.pages.settings.SettingsViewModel
import com.ez.weather4you.ui.pages.weatherforecast.LocationsForecastPage
import com.ez.weather4you.ui.pages.weatherforecast.LocationsForecastViewModel
import kotlinx.serialization.serializer

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun W4YNavigation(initialPage: W4YNavKey) {

    val backStack = rememberW4YNavBackStack(initialPage)
    val sheetState = rememberModalBottomSheetState(skipPartiallyExpanded = true)
    val bottonSheetStrategy = remember { BottomSheetSceneStrategy<W4YNavKey>(sheetState) }

    NavDisplay(
        backStack = backStack,
        onBack = { backStack.removeLastOrNull() },
        sceneStrategies = listOf(bottonSheetStrategy),
        entryDecorators = listOf(
            // Add the default decorators for managing scenes and saving state
            rememberSaveableStateHolderNavEntryDecorator(),
            // Then add the view model store decorator
            rememberViewModelStoreNavEntryDecorator()
        ),
        entryProvider = entryProvider {
            entry<LocationsForecastPageKey> {
                val viewModel =
                    hiltViewModel<LocationsForecastViewModel, LocationsForecastViewModel.Factory>(
                        creationCallback = { factory ->
                            factory.create(backStack)
                        }
                    )
                val state by viewModel.uiState.collectAsState()
                LocationsForecastPage(state)
            }
            entry<SearchPageKey>(metadata = BottomSheetSceneStrategy.bottomSheet()) {
                val viewModel: SearchViewModel = viewModel()
                val state by viewModel.uiState.collectAsState()
                SearchPage(state = state)
            }
            entry<SettingsPageKey> {
                SettingsPage(onBack = { backStack.removeLastOrNull() })
            }
            entry<OnboardingPageKey> {
                val viewModel =
                    hiltViewModel<OnboardingViewModel, OnboardingViewModel.Factory>(
                        creationCallback = { factory ->
                            factory.create(backStack)
                        }
                    )
                OnboardingPage(
                    onPermissionRequested = { viewModel.onPermissionRequested() },
                    onPermissionNotRequested = { viewModel.navigateToWeatherForecastPage() })
            }
        }

//        entryProvider = { key ->
//            when (key) {
//                is LocationsForecastPageKey -> NavEntry(key) {
//                    val viewModel: LocationsForecastViewModel = viewModel()
//                    val state by viewModel.state.collectAsState()
//                    LocationsForecastPage(state)
//                }
//                is SearchPageKey -> NavEntry(key) {
//                    val viewModel: SearchViewModel = viewModel()
//                    val model by viewModel.model.collectAsState()
//                    val sheetState = rememberModalBottomSheetState(skipPartiallyExpanded = true)
//                }
//            }
//        }
    )
}

@Composable
fun rememberW4YNavBackStack(vararg elements: W4YNavKey): NavBackStack<W4YNavKey> {
    return rememberSerializable(serializer = serializer()) {
        NavBackStack(*elements)
    }
}
