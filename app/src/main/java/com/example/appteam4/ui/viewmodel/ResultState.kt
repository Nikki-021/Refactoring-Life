package com.example.appteam4.ui.viewmodel

sealed class ResultState<out T> {
    object Loading : ResultState<Nothing>()
    data class Success<out T>(val token: T) : ResultState<T>()
    data class Error(val message: String) : ResultState<Nothing>()
}
