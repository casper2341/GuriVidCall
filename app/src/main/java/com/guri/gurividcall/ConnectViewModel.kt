package com.guri.gurividcall

import android.app.Application
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.AndroidViewModel

class ConnectViewModel(
    private val app: Application
) : AndroidViewModel(app) {

    var state by mutableStateOf(ConnectState())
        private set

    fun onAction(action: ConnectAction) {
        when (action) {
            is ConnectAction.onConnectClick -> {
                connectToRoom()
            }

            is ConnectAction.onNameChange -> {
                state = state.copy(name = action.name)
            }
        }
    }

    private fun connectToRoom() {
        state = state.copy(errorMessage = null)
        if (state.name.isBlank()) {
            state = state.copy(errorMessage = "The username can`t be blank")
            return
        }

        (app as GuriVidCallApplication).initVideoClient(state.name)

        state = state.copy(isConnected = true)
    }
}