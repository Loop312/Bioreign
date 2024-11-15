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
    var characterMenuOpen by mutableStateOf(false)
    var selectModeOpen by mutableStateOf(false)
    @Composable
    override fun open(){
        if(!gameLoop.isPlaying && isOpen) {
            homeMenu()
            selectMode()
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
                Button(onClick = { settingsMenu.isOpen = true; isOpen = false}) {
                    Text("Settings")
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
                    Button(onClick = { storymode.saveMenuOpen = true; selectModeOpen = false }) {
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
}