package com.ez.weather4you.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.ez.weather4you.ui.navigation.W4YNavKey
import com.ez.weather4you.ui.navigation.W4YNavigation
import dagger.hilt.android.AndroidEntryPoint
import androidx.compose.runtime.collectAsState

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val viewModel: MainActivityViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        val splashScreen = installSplashScreen()
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        splashScreen.setKeepOnScreenCondition { viewModel.firstPageNavigationKey.value == null }
        viewModel.firstPageNavigationKey.value
        setContent {
            viewModel.firstPageNavigationKey.collectAsState().value?.let { W4YNavigation(it) }
        }
    }
}