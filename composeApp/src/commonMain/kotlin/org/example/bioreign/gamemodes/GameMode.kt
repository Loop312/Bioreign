package org.example.bioreign.gamemodes

import androidx.compose.runtime.Composable
import org.example.bioreign.gameLoop
import org.example.bioreign.pregameMenu

open class GameMode {
    var isPlaying = false

    @Composable
    open fun play() {

    }
    fun refresh(){
        isPlaying = !isPlaying
        isPlaying = !isPlaying
    }
    fun toHomeMenu() {
        pregameMenu.isOpen = true
        pregameMenu.homeMenuOpen = true
        gameLoop.isPlaying = false
        isPlaying = false
    }
}