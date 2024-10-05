//compose imports
import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.*
import androidx.compose.ui.window.*
import androidx.compose.ui.*
import androidx.compose.ui.input.key.*
import androidx.compose.ui.res.*
import androidx.compose.ui.unit.*

import characters.*
import maps.*
import menus.*

val player = Player()
val gameLoop = GameLoop()
val keyListener = KeyListener()
val map = Map("maps/Placeholder.jpg")
val hud = HUD()
val gameMenu = InGameMenu()
val pregameMenu = PregameMenu()
val editKeysMenu = EditKeysMenu()
val debugger = DebugWindow()

@Composable
fun game(){
    //open the pregame menu and wait to start the game
    if (gameLoop.isplaying) {
        Box {
            map.load()
            Image(
                painter = painterResource("TestCircle.png"),
                contentDescription = "lol",
                modifier = Modifier
                    .offset(player.x.dp, player.y.dp)
                    .size(100.dp)
                    .align(Alignment.Center)
            )
            Column {
                hud.healthBar()
                hud.staminaBar()
            }
            gameMenu.open()
        }
    }
}

@Composable
@Preview
fun App() {
    game()
    gameLoop.GameScreen()
}

fun main() = application {
    //initialize a set for handling key inputs

    map.addCollider("up", arrayOf(100,-100,100))
    player.movingUp = true

    //initialize window
    Window(onCloseRequest = ::exitApplication, title = "Bioreign", onKeyEvent = { event: KeyEvent ->
        //when a button is pressed add it to pressedKeys
        when (event.type) {
            KeyEventType.KeyDown -> {
                keyListener.pressedKeys += event.key
            }
            KeyEventType.KeyUp -> {
                keyListener.pressedKeys -= event.key
            }
        }
        true
    }) {
        pregameMenu.open()
        editKeysMenu.open()
        keyListener.listen()
        if (gameLoop.isplaying) {
            App()
            map.checkCollisions()
            //println("player Coordinates (" + map.x + ", " + map.y + ")")
        }
    }
    debugger.open()
    //println("LEVEL UP SIMULATOR TEST\n\n")
    //player.lvlupSim(5)
}