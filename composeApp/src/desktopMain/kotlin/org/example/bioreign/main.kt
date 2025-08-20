package org.example.bioreign

//compose imports
import androidx.compose.ui.window.*

val debugger = DebugWindow()

fun main() = application {
    //initialize a set for handling key inputs

    map.addCollider("up", arrayOf(100,-100,100))
    player.movingUp = true

    //initialize window
    Window(onCloseRequest = ::exitApplication, title = "Bioreign", alwaysOnTop = true) {
        Bioreign()
    }
    debugger.open()
    //println("LEVEL UP SIMULATOR TEST\n\n")
    //player.lvlupSim(5)
}