import androidx.compose.foundation.layout.Column
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.*

class DebugWindow {
    var open by mutableStateOf(false)

    @Composable
    fun open(){
        if (open) {
            Window(
                title = "Debug", // Optional: Set a title for the window
                onCloseRequest = {open = false}, // Handle window closing
                state = rememberWindowState(size = DpSize(300.dp, 400.dp))
            ) {
                Column {
                    Text("Player Coordinates: (" + map.x + "," + map.y + ")\n")
                    Text(
                        "Colliders: " +
                                //figure out how to for loop
                                if (player.movingUp) "UP: " + map.displayColliders("up")
                                else if (player.movingDown) "DOWN: " + map.displayColliders("down")
                                else if (player.movingLeft) "LEFT: " + map.displayColliders("left")
                                else "RIGHT: " + map.displayColliders("right")
                    )
                    Text("\n" + keyListener.pressedKeys.toString())
                    Text("\nStats:\n" + player.displayStats())
                }
            }
        }
    }
}