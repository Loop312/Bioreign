import androidx.compose.runtime.*
import characters.*
import kotlinx.coroutines.*

class GameLoop() {
    //might add array of characters, maps, and other stuff as parameters
    @Composable
    fun GameScreen(player: Character) {
        var gameState by remember { mutableStateOf(true) }

        LaunchedEffect(Unit) {
            val startTime = System.nanoTime()
            var lastFrameTime = startTime

            while (true) {
                val currentTime = System.nanoTime()
                val deltaTime = (currentTime - lastFrameTime) / 1_000_000_000.0 // seconds

                // Update game state based on deltaTime
                //gameState = updateGameState(gameState, deltaTime)

                //if (player.x < 500) player.x += 1


                // Render the game state
                // ...

                lastFrameTime = currentTime

                // Handle potential delays or throttling
                delay(10) // Adjust delay for target frame rate
            }
        }
        // Compose UI for rendering the game state
        // ...
    }
}
