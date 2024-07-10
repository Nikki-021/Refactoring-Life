package com.example.appteam4.ui.viewmodel

import com.example.appteam4.model.response.ResponseLogin

sealed class LoginEvent {
    data class Success(val value: ResponseLogin) : LoginEvent()
    data object Loading : LoginEvent()
    data class Error(val message: String) : LoginEvent()
}
