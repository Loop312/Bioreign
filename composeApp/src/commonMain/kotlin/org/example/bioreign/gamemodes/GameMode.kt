package org.example.bioreign.gamemodes

import androidx.compose.runtime.Composable

open class GameMode {
    var isPlaying = false

    @Composable
    open fun play() {

    }
    /*
    fun toHomeMenu() {
        pregameMenu.isOpen = true
        pregameMenu.homeMenuOpen = true
        gameLoop.isPlaying = false
        isPlaying = false
    }

    fun startGame() {
        isPlaying = true
        gameLoop.isPlaying = true
        keyListener.edit = false
    }
     */
}