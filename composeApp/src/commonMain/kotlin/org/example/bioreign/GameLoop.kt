package org.example.bioreign

import androidx.compose.runtime.*
import kotlinx.coroutines.*

class GameLoop {
    var isPlaying by mutableStateOf(false)
    //might add array of characters, maps, and other stuff as parameters
    @Composable
    fun GameScreen() {
        var gameState by remember { mutableStateOf(true) }

        LaunchedEffect(Unit) {
            //val startTime = System.nanoTime()
            //var lastFrameTime = startTime

            while (true) {
                if(isPlaying) {
                    //val currentTime = System.nanoTime()
                    //val deltaTime = (currentTime - lastFrameTime) / 1_000_000_000.0 // seconds

                    // Update game state based on deltaTime
                    //gameState = updateGameState(gameState, deltaTime)

                    //if (player.x > 500) map.mapEdge = true

                    // Render the game state
                    // ...

                    //lastFrameTime = currentTime
                    /*
                    if (player.hp > 0) {
                        player.physDmg(11)
                        println("player hp: " + player.hp)
                    }
                    println("player stamina: " + player.stamina)
                    */

                    if (keyListener.esc == false) {
                        keyListener.esc = true
                    }
                    player.exp += 0.5F
                }

                // Handle potential delays or throttling
                delay(1000/60) // Adjust delay for target frame rate
            }
        }
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
                        player.stamina += 0.1
                        delay(1000/60)
                    }
                }
            }
        }
        //code runs whenever exp changes
        LaunchedEffect(player.exp){
            if (player.exp >= player.explimit) player.lvlup()
        }
    }
}
