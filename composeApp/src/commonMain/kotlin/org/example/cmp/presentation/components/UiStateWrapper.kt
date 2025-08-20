package org.example.cmp.presentation.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import org.example.cmp.presentation.states.UiState

@Composable
fun <T> UiStateWrapper(
    state: UiState<T>,
    onRetry: () -> Unit,
    modifier: Modifier = Modifier,
    content: @Composable (data: T) -> Unit
) {
    Box(modifier = modifier.fillMaxSize()) {
        when (state) {
            is UiState.Loading -> {
                LoadingComponent()
            }
            is UiState.Success -> {
                content(state.data)
            }
            is UiState.Error -> {
                ErrorComponent(
                    message = state.message,
                    onRetry = onRetry
                )
            }
        }
    }
} 