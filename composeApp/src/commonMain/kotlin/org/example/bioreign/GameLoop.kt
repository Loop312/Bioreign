package org.example.bioreign

import androidx.compose.runtime.*
import kotlinx.coroutines.*

class GameLoop() {
    var isplaying by mutableStateOf(false)
    //might add array of characters, maps, and other stuff as parameters
    @Composable
    fun GameScreen() {
        var gameState by remember { mutableStateOf(true) }

        LaunchedEffect(Unit) {
            //val startTime = System.nanoTime()
            //var lastFrameTime = startTime

            while (true) {
                if(isplaying) {
                    //val currentTime = System.nanoTime()
                    //val deltaTime = (currentTime - lastFrameTime) / 1_000_000_000.0 // seconds

                    // Update game state based on deltaTime
                    //gameState = updateGameState(gameState, deltaTime)

                    //if (player.x > 500) map.mapEdge = true

                    // Render the game state
                    // ...

                    //lastFrameTime = currentTime
                    if (player.hp > 0) {
                        player.physDmg(11)
                        println("player hp: " + player.hp)
                    }
                    println("player stamina: " + player.stamina)

                    if (!player.sprinting && player.stamina < player.maxStamina) {
                        player.stamina += .05
                    }

                    if (keyListener.esc == false) {
                        keyListener.esc = true
                    }
                }

                // Handle potential delays or throttling
                delay(1000/60) // Adjust delay for target frame rate
            }
        }
        // Compose UI for rendering the game state
        // ...
    }
}
