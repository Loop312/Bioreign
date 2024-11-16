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
import bioreign.composeapp.generated.resources.Res
import bioreign.composeapp.generated.resources.compose_multiplatform
import org.example.bioreign.*

class Storymode: GameMode() {
    var Saves by mutableStateOf(arrayOf("1: New Game", "2: New Game", "3: New Game", "4: New Game", "5: New Game"))
    var saveMenuOpen by mutableStateOf(false)
    @Composable
    fun saveMenu() {
        if (saveMenuOpen) {
            Box(Modifier.fillMaxSize()) {
                Column (Modifier.align(Alignment.TopEnd)) {
                    for (i in 0..4) {
                        Button(onClick = {
                            isPlaying = true
                            saveMenuOpen = false
                            gameLoop.isPlaying = true
                            keyListener.edit = false
                        }) {
                            Text(Saves[i])
                        }
                    }
                }
            }
        }
    }
    @Composable
    override fun play() {
        saveMenu()
        if (isPlaying) {
            Box {
                map.load()
                player.load(Res.drawable.compose_multiplatform)
                hud.display()
                gameMenu.open()
                gameLoop.GameScreen()
                gameLoop.playerStuff()
                map.checkCollisions()
                Button(onClick = {toHomeMenu()}, Modifier.align(Alignment.BottomCenter)) {
                    Text("Back")
                }
            }
        }
    }
}