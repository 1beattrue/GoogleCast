package edu.mirea.onebeattrue.googlecast.presentation

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Cast
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import edu.mirea.onebeattrue.googlecast.R

@Composable
fun MainScreen(
    modifier: Modifier = Modifier,
) {
    val viewModelFactory = applicationComponent.getViewModelFactory()
    val viewModel: MainViewModel = viewModel(factory = viewModelFactory)
    val state by viewModel.state.collectAsStateWithLifecycle()

    val videoUrl = stringResource(R.string.video_url)

    Box(
        modifier = modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Column(
            verticalArrangement = Arrangement.spacedBy(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            IconButton(onClick = { viewModel.castVideo(videoUrl) }) {
                Icon(
                    imageVector = Icons.Default.Cast,
                    contentDescription = stringResource(R.string.content_description)
                )
            }
            when (val currentState = state) {
                State.Initial -> {
                    Text(stringResource(R.string.initial))
                }

                is State.Error -> {
                    Text(
                        text = currentState.throwable.message?.let { message ->
                            stringResource(R.string.error, message)
                        } ?: stringResource(R.string.unknown_error),
                        color = MaterialTheme.colorScheme.error
                    )
                }

                State.Success -> {
                    Text(stringResource(R.string.success))
                }
            }
        }
    }
}