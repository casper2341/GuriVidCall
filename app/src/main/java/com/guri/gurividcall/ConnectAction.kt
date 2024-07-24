package com.guri.gurividcall

sealed interface ConnectAction {
    data class onNameChange(val name: String) : ConnectAction
    data object onConnectClick : ConnectAction
}