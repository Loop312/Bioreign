package menus

import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import gameLoop


class PregameMenu {
    @Composable
    fun open(){
        if(!gameLoop.isplaying) {
            Box {
                Button(onClick = {gameLoop.isplaying = true }) {
                    Text("Start Game")
                }
            }
        }
    }
}