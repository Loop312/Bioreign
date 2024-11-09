package org.example.bioreign.gamemodes

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import org.example.bioreign.gameLoop
import org.example.bioreign.keyListener

class Storymode {
    var Saves by mutableStateOf(arrayOf("1: New Game", "2: New Game", "3: New Game", "4: New Game", "5: New Game"))
    var saveMenuOpen by mutableStateOf(false)
    var playing by mutableStateOf(false)
    @Composable
    fun saveMenu() {
        if (saveMenuOpen) {
            Box(Modifier.fillMaxSize()) {
                Column (Modifier.align(Alignment.TopEnd)) {
                    for (i in 0..4) {
                        Button(onClick = {saveMenuOpen = false; playing = true}) {
                            Text(Saves[i])
                        }
                    }
                }
            }
        }
        else {
            play()
        }
    }
    @Composable
    fun play() {
        if (playing) {
            gameLoop.isPlaying = true
            keyListener.edit = false
        }
    }
}