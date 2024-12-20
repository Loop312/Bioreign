package org.example.bioreign

//compose imports
import androidx.compose.ui.window.*
//import androidx.compose.ui.input.key.*

val debugger = DebugWindow()
/*
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
fun theApp() {
    game()
    gameLoop.GameScreen()
}
*/

fun main() = application {
    //initialize a set for handling key inputs

    map.addCollider("up", arrayOf(100,-100,100))
    player.movingUp = true

    //initialize window
    Window(onCloseRequest = ::exitApplication, title = "Bioreign",/* onKeyEvent = { event: KeyEvent ->
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
    }*/) {
        /*
        pregameMenu.open()
        editKeysMenu.open()
        keyListener.listen()
        if (gameLoop.isplaying) {
            theApp()
            map.checkCollisions()
            //println("player Coordinates (" + map.x + ", " + map.y + ")")
        }
         */
        theApp()
    }
    debugger.open()
    //println("LEVEL UP SIMULATOR TEST\n\n")
    //player.lvlupSim(5)
}