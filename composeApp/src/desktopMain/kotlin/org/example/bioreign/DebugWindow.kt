package org.example.bioreign

import androidx.compose.runtime.*
import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.*

class DebugWindow {
    var open by mutableStateOf(false)

    @Composable
    fun open(){
        if (open) {
            Window(
                title = "Debug", // Optional: Set a title for the window
                onCloseRequest = {open = false}, // Handle window closing
                state = rememberWindowState(size = DpSize(300.dp, 400.dp))
            ) {

            }
        }
    }
}