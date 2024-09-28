package menus

import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import editKeysMenu
import gameLoop
import keyListener


class PregameMenu: Menu() {
    override var isOpen by mutableStateOf(true)
    @Composable
    override fun open(){
        if(!gameLoop.isplaying && isOpen) {
            Column {
                Button(onClick = {gameLoop.isplaying = true; isOpen = false; keyListener.edit = false}) {
                    Text("Start Game")
                }
                Button(onClick = {editKeysMenu.isOpen = true; isOpen = false; keyListener.edit = true}){
                    Text("Edit Keys")
                }
            }
        }
    }
}