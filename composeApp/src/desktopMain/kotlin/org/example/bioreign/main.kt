package org.example.bioreign

//compose imports
import androidx.compose.ui.window.*

val debugger = DebugWindow()

fun main() = application {
    //initialize a set for handling key inputs
    //initialize window
    Window(onCloseRequest = ::exitApplication, title = "Bioreign", alwaysOnTop = true) {
        Bioreign()
    }
    debugger.open()
    //println("LEVEL UP SIMULATOR TEST\n\n")
    //player.lvlupSim(5)
}