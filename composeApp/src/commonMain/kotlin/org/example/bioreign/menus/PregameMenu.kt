package menus

import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import org.example.bioreign.*


class PregameMenu: Menu() {
    override var isOpen by mutableStateOf(true)
    var homeMenuOpen by mutableStateOf(true)
    var Saves by mutableStateOf(arrayOf("1: New Game", "2: New Game", "3: New Game", "4: New Game", "5: New Game"))
    var saveMenuOpen by mutableStateOf(false)
    var characterMenuOpen by mutableStateOf(false)
    var selectModeOpen by mutableStateOf(false)
    @Composable
    override fun open(){
        if(!gameLoop.isPlaying && isOpen) {
            homeMenu()
            selectMode()
            saveMenu()
        }
    }

    @Composable
    fun homeMenu() {
        if (homeMenuOpen) {
            Column {
                Button(onClick = { homeMenuOpen = false; selectModeOpen = true; keyListener.edit = false }) {
                    Text("Start Game")
                }
                Button(onClick = { editKeysMenu.isOpen = true; isOpen = false; keyListener.edit = true }) {
                    Text("Edit Keys")
                }
            }
        }
    }
    //Available Game Modes: Story, PvP, roguelike
    @Composable
    fun selectMode() {
        if (selectModeOpen) {
            Box(Modifier.fillMaxSize()) {
                Row(Modifier.align(Alignment.Center)) {
                    Button(onClick = { saveMenuOpen = true; selectModeOpen = false }) {
                        Text("Story Mode")
                    }
                    Button(onClick = { selectCharacter() }) {
                        Text("PvP Mode")
                    }
                    Button(onClick = { selectCharacter() }) {
                        Text("Roguelike Mode")
                    }
                }
            }
        }
    }

    fun selectCharacter() {
        //TODO
        if (characterMenuOpen) {

        }
    }

    @Composable
    fun saveMenu() {
        if (saveMenuOpen) {
            Box(Modifier.fillMaxSize()) {
                Column (Modifier.align(Alignment.TopEnd)) {
                    for (i in 0..4) {
                        Button(onClick = { gameLoop.isPlaying = true; isOpen = false; keyListener.edit = false }) {
                            Text(Saves[i])
                        }
                    }
                }
            }
        }
    }
}