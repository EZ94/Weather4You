package com.ez.weather4you.ui.pages.onboarding

import android.Manifest
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.FilledTonalButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.ez.weather4you.R
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.rememberPermissionState

@OptIn(ExperimentalPermissionsApi::class)
@Composable
fun OnboardingPage(onPermissionRequested: () -> Unit, onPermissionNotRequested: () -> Unit) {
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        containerColor = MaterialTheme.colorScheme.primary
    ) { innerPadding ->


        val locationPermissions = rememberLauncherForActivityResult(
            contract = ActivityResultContracts.RequestMultiplePermissions()
        ) {
            onPermissionRequested()
        }

//        val locationPermissions =
//            rememberPermissionState(Manifest.permission.ACCESS_COARSE_LOCATION) {onPermissionRequested()}


        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceEvenly
        ) {
            Image(

                modifier = Modifier.padding(16.dp),
                painter = painterResource(id = R.drawable.location_permission),
                contentDescription = "Location permission"
            )
            Column(
                modifier = Modifier.padding(horizontal = 16.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = stringResource(R.string.location_permission_title),
                    style = MaterialTheme.typography.titleLarge,
                    color = MaterialTheme.colorScheme.onPrimary
                )
                Text(
                    text = stringResource(R.string.this_app_would_like_to_access_your_location),
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onPrimary,
                    textAlign = TextAlign.Center
                )
            }
            Column(
                modifier = Modifier.padding(horizontal = 16.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                FilledTonalButton(
                    modifier = Modifier.fillMaxWidth(), onClick = {
                        locationPermissions.launch(
                            arrayOf(
                                Manifest.permission.ACCESS_COARSE_LOCATION,
                                Manifest.permission.ACCESS_FINE_LOCATION
                            )
                        )
//                        locationPermissions.launchPermissionRequest()
                    }) {
                    Text(
                        text = stringResource(R.string.location_permission_button_current),
                        color = MaterialTheme.colorScheme.onSurface
                    )
                }
                TextButton(
                    modifier = Modifier.fillMaxWidth(),
                    onClick = { onPermissionNotRequested() },
                    colors = ButtonColors(
                        containerColor = MaterialTheme.colorScheme.primary,
                        contentColor = MaterialTheme.colorScheme.onPrimary,
                        disabledContainerColor = MaterialTheme.colorScheme.primary,
                        disabledContentColor = MaterialTheme.colorScheme.onPrimary
                    )
                ) {
                    Text(text = stringResource(R.string.location_permission_button_search))
                }
            }

        }
    }
}

@Preview(showBackground = true)
@Composable
fun OnboardingPagePreview() {
    OnboardingPage(onPermissionRequested = {}, onPermissionNotRequested = {})
}