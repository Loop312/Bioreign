package org.example.bioreign.gamemodes

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier

class StoryMode {
    @Composable
    fun play(toHomeMenu: () -> Unit) {
        Box(Modifier.fillMaxSize()) {
            Button(onClick = {toHomeMenu()}, Modifier.align(Alignment.BottomCenter)) {
                Text("Home")
            }
        }
    }
}