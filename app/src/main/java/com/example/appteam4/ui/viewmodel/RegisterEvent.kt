package com.example.appteam4.ui.viewmodel

import com.example.appteam4.model.response.ResponseRegister

sealed class RegisterEvent {
    data class Successs(val value: ResponseRegister) : RegisterEvent()
    data object Loading : RegisterEvent()
    data class Error(val message: String) : RegisterEvent()
}
