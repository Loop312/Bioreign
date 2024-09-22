package menus

import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import gameLoop


class PregameMenu {
    var isOpen by mutableStateOf(true)
    @Composable
    fun open(){
        if(!gameLoop.isplaying && isOpen) {
            Box {
                Button(onClick = {gameLoop.isplaying = true; isOpen = false}) {
                    Text("Start Game")
                }
            }
        }
    }
}