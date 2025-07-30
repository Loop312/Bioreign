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
    Window(onCloseRequest = ::exitApplication, title = "Bioreign", alwaysOnTop = true) {
        theApp()
    }
    debugger.open()
    //println("LEVEL UP SIMULATOR TEST\n\n")
    //player.lvlupSim(5)
}