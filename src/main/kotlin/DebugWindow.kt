import androidx.compose.foundation.layout.Column
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.*

class DebugWindow {

    @Composable
    fun open(){
        Window(
            title = "Debug", // Optional: Set a title for the window
            onCloseRequest = {}, // Handle window closing
            state = rememberWindowState(size = DpSize(400.dp, 300.dp))
        ){
            Column {
                Text("Player Coordinates: (" + map.x + "," + map.y + ")")
                Text("Colliders: " +
                        //figure out how to for loop
                        if (player.movingUp) "[" + map.upColliders[0][0].toString() + "," + map.upColliders[0][1].toString() + "," + map.upColliders[0][2].toString() + "]"
                        else if (player.movingDown) map.downColliders[0]
                        else if (player.movingLeft) map.leftColliders[0]
                        else map.rightColliders[0]
                )
                Text("Stats:\n" + player.displayStats())
            }
        }
    }
}