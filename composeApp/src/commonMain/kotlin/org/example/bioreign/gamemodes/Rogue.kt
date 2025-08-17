package org.example.bioreign.gamemodes

import androidx.compose.foundation.layout.Box
import androidx.compose.material.Button
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.Alignment
import androidx.compose.material.Text
import org.example.bioreign.*

class Rogue {
    @Composable
    fun play(toHomeMenu: () -> Unit) {
        Box {
            map.load()
            player.load()
            map.checkCollisions()
            Button(onClick = {toHomeMenu()}, Modifier.align(Alignment.BottomCenter)) {
                Text("Back")
            }
        }
    }
}