package org.example.bioreign.gamemodes

import androidx.compose.foundation.focusable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.input.key.onKeyEvent
import org.example.bioreign.*

class StoryMode {
    @Composable
    fun play(toHomeMenu: () -> Unit) {
        val focusRequester = remember { FocusRequester() }
        LaunchedEffect(Unit) {
            focusRequester.requestFocus()
        }
        Box(Modifier.fillMaxSize().focusRequester(focusRequester).focusable().onKeyEvent(keyHandler.listen)) {
            map.load()
            player.load()
            hud.display()
            //gameMenu.open()
            gameLoop.GameScreen()
            gameLoop.playerStuff()
            map.checkCollisions()

            Button(onClick = {toHomeMenu()}, Modifier.align(Alignment.BottomCenter)) {
                Text("Home")
            }
        }
    }
}