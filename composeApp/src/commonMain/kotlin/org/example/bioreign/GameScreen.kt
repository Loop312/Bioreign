package org.example.bioreign

import androidx.compose.runtime.*
import kotlinx.coroutines.*

class GameScreen {
    var isPlaying by mutableStateOf(false)
    //might add array of characters, maps, and other stuff as parameters

    @Composable
    fun show() {
        var gameState by remember { mutableStateOf(true) }
        gameLoop()
        playerStuff()
        // Compose UI for rendering the game state
        // ...
    }
    @Composable
    fun playerStuff(){
        //code runs whenever sprinting changes
        LaunchedEffect(player.sprinting) {
            if (!player.sprinting) {
                delay(1000)
                launch {
                    while(player.stamina < player.maxStamina) {
                        player.stamina += 0.1 * frameRateMultiplier
                        delay(frameRate.toLong())
                    }
                }
            }
        }
        //code runs whenever exp changes
        LaunchedEffect(player.exp){
            if (player.exp >= player.explimit) player.lvlup()
        }
        //code runs whenever sprinting changes
        LaunchedEffect(player.casting) {
            if (!player.casting) {
                delay(1000)
                launch {
                    while(player.mana < player.maxMana) {
                        player.mana += 0.1 * frameRateMultiplier
                        delay(frameRate.toLong())
                    }
                }
            }
        }
    }
    @Composable
    fun gameLoop() {
        var fps by remember { mutableStateOf(0f) }
        var frameTime by remember { mutableStateOf(0f) }
        var fpsUpdateTimer = remember { 0f }
        var updateInterval = remember { .5f } //in seconds
        LaunchedEffect(Unit) {
            var lastFrameTimeNanos = 0L
            while (true) {
                withFrameNanos { frameTimeNanos: Long ->
                    val deltaTime = if (lastFrameTimeNanos == 0L) {
                        0f //can't do 0/1_000_000_000f
                    } else {
                        (frameTimeNanos - lastFrameTimeNanos) / 1_000_000_000f
                    }
                    fpsUpdateTimer += deltaTime
                    if (fpsUpdateTimer >= updateInterval) {
                        frameTime = deltaTime * 1000
                        fps = 1 / deltaTime
                        fpsUpdateTimer = 0f
                    }
                    lastFrameTimeNanos = frameTimeNanos

                    update(deltaTime)
                }
            }
        }
        //move to GameScreen.show later
        hud.display(fps, frameTime)
    }

    fun update(deltaTime: Float) {
        player.exp += 1f * deltaTime
    }
}
