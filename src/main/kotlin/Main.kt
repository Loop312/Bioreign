//compose imports
import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.*
import androidx.compose.ui.window.*
import androidx.compose.ui.*
import androidx.compose.ui.input.key.*
import androidx.compose.ui.res.*
import androidx.compose.ui.unit.dp
import characters.*

val player = Human()

@Composable
fun test(){
    Image(
        painter = painterResource("TestCircle.png"),
        contentDescription = "lol",
        modifier = Modifier
            .offset (player.x.dp, player.y.dp)
            .size(100.dp)
    )
}

@Composable
@Preview
fun App() {
    test()
}

fun main() = application {
    var pressedKeys by remember { mutableStateOf<Set<Key>>(emptySet()) }
    Window(onCloseRequest = ::exitApplication, title = "Bioreign", onKeyEvent = { event: KeyEvent ->
        when (event.type) {
            KeyEventType.KeyDown -> {
                pressedKeys += event.key
            }
            KeyEventType.KeyUp -> {
                pressedKeys -= event.key
            }
        }
        true
    }) {
        App()
        if (Key.A in pressedKeys || Key.DirectionLeft in pressedKeys) {
            player.x -= 5
        }
        if (Key.D in pressedKeys || Key.DirectionRight in pressedKeys) {
            player.x += 5
        }
        if (Key.W in pressedKeys || Key.DirectionUp in pressedKeys) {
            player.y -= 5
        }
        if (Key.S in pressedKeys || Key.DirectionDown in pressedKeys) {
            player.y += 5
        }
    }

    //println("LEVEL UP SIMULATOR TEST\n\n")
    //player.lvlupSim(5)
}