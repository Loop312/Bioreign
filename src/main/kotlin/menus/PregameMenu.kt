package menus

import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import debugger
import editKeysMenu
import gameLoop
import keyListener


class PregameMenu: Menu() {
    override var isOpen by mutableStateOf(true)
    @Composable
    override fun open(){
        if(!gameLoop.isplaying && isOpen) {
            Column {
                Button(onClick = {gameLoop.isplaying = true; isOpen = false}) {
                    Text("Start Game")
                }
                Button(onClick = {editKeysMenu.isOpen = true; isOpen = false; keyListener.edit = true}){
                    Text("Edit Keys")
                }
                Button(onClick = {debugger.open = true}){
                    Text("Open Debugger")
                }
            }
        }
    }
}