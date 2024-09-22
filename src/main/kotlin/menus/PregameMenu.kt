package menus

import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.input.key.Key
import gameLoop
import keyListener


class PregameMenu {
    var isOpen by mutableStateOf(true)
    @Composable
    fun open(){
        if(!gameLoop.isplaying && isOpen) {
            Column {
                Button(onClick = {gameLoop.isplaying = true; isOpen = false}) {
                    Text("Start Game")
                }
                Button(onClick = { println("need to add function for changing keybinds")}){
                    Text("Edit Keys")
                }
            }
        }
    }
    //needs changes to how it's setup and the way it calls the onClick function
    @Composable
    fun editKeys(){
        Column {
            for(i in 0..keyListener.keys.size - 1){
                Row{
                    Button(onClick = {keyListener.editKeybinds(i, Key.A)}){
                        Text(keyListener.keys[i].toString())
                    }
                }
            }
        }
    }
}