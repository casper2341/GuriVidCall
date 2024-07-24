package com.guri.gurividcall

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.guri.gurividcall.firebase.FirebaseConfig
import com.guri.gurividcall.ui.theme.GuriVidCallTheme

@Composable
fun ConnectScreen(state: ConnectState, onAction: (ConnectAction) -> Unit) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
        ) {
            Text(text = "Choose a name", fontSize = 18.sp)
            Spacer(modifier = Modifier.height(16.dp))
            TextField(
                modifier = Modifier.fillMaxWidth(),
                value = state.name,
                onValueChange = { onAction(ConnectAction.onNameChange(it)) },
                placeholder = {
                    Text("Enter your name")
                },
            )
            Spacer(modifier = Modifier.height(16.dp))
            Button(onClick = {
                onAction(ConnectAction.onConnectClick)
            }) {
                Text("Connect")
            }
            Spacer(modifier = Modifier.height(16.dp))
            if (state.errorMessage != null) {
                Text(text = state.errorMessage)
            }
            Text(FirebaseConfig.getInstance().getString("color"))
        }
}

@Preview(showBackground = true)
@Composable
private fun ConnectScreenPreview() {
    GuriVidCallTheme {
        ConnectScreen(
            state = ConnectState(),
            onAction = { }
        )
    }
}