package org.example.cmp.utils

import org.example.cmp.presentation.states.UiState

// UiState extension functions
fun <T> UiState<T>.isLoading(): Boolean = this is UiState.Loading
fun <T> UiState<T>.isSuccess(): Boolean = this is UiState.Success
fun <T> UiState<T>.isError(): Boolean = this is UiState.Error

fun <T> UiState<T>.getDataOrNull(): T? = if (this is UiState.Success) data else null
fun <T> UiState<T>.getErrorOrNull(): String? = if (this is UiState.Error) message else null

// String extensions
fun String?.orDefault(default: String): String = if (this.isNullOrBlank()) default else this
