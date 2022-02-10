package com.ronnie.presentation.utils

sealed class UIState<out T> {
    data class Success<out T>(val value: T) : UIState<T>()
    data class Error(
        val errorMessage: String?
    ) : UIState<Nothing>()
    object Loading : UIState<Nothing>()
}