package com.ez.weather4you.ui.pages.weatherforecast.screens.location.components.error

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Card
import androidx.compose.material3.CardColors
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import androidx.compose.ui.unit.dp
import com.ez.weather4you.R

@Composable
fun ErrorComponent(modifier: Modifier, model: ErrorComponentUIModel) {
    Card(
        modifier = modifier.fillMaxWidth(),
        colors = MaterialTheme.colorScheme.let {
            CardColors(
                containerColor = it.tertiaryContainer,
                contentColor = it.onTertiaryContainer,
                disabledContainerColor = it.tertiaryContainer,
                disabledContentColor = it.onTertiaryContainer
            )
        }
    ) {
        Row(modifier = Modifier.padding(8.dp), verticalAlignment = Alignment.CenterVertically) {
            Box(Modifier.size(24.dp)) {
                when (model.type) {
                    ErrorComponentUIModel.Type.LOADING -> CircularProgressIndicator(
                        Modifier.fillMaxSize(),
                        color = MaterialTheme.colorScheme.onTertiaryContainer
                    )

                    ErrorComponentUIModel.Type.ERROR -> Icon(
                        painter = painterResource(R.drawable.outline_error_outline_24),
                        contentDescription = stringResource(R.string.error_icon),
                        tint = MaterialTheme.colorScheme.onTertiaryContainer
                    )
                }

            }
            Spacer(Modifier.size(width = 8.dp, height = 0.dp))
            Text(
                modifier = Modifier.fillMaxWidth(),
                text = stringResource(model.message),
                style = MaterialTheme.typography.labelLarge
            )
        }
    }
}

@Composable
@Preview
fun ErrorComponentPreview(@PreviewParameter(ErrorComponentUIModelProvider::class) model: ErrorComponentUIModel) {
    ErrorComponent(modifier = Modifier.padding(16.dp), model)
}


class ErrorComponentUIModelProvider :
    PreviewParameterProvider<ErrorComponentUIModel> {
    override val values: Sequence<ErrorComponentUIModel> = sequenceOf(
        ErrorComponentUIModel(
            message = R.string.out_of_date_error,
            type = ErrorComponentUIModel.Type.ERROR
        ),
        ErrorComponentUIModel(
            message = R.string.out_of_date_refreshing,
            type = ErrorComponentUIModel.Type.LOADING
        )
    )
}
