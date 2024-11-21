package org.example.bioreign.menus

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import bioreign.composeapp.generated.resources.BioreignTempLogo
import bioreign.composeapp.generated.resources.Res
import org.example.bioreign.*
import org.jetbrains.compose.resources.painterResource


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
            Box(Modifier.fillMaxSize()) {
                Image(painterResource(Res.drawable.BioreignTempLogo), null, Modifier.align(Alignment.TopCenter).offset(0.dp, 50.dp))
                Column (Modifier.align(Alignment.Center)) {
                    Button(onClick = { homeMenuOpen = false; selectModeOpen = true; keyListener.edit = false }) {
                        Text("Start Game")
                    }
                    Button(onClick = { editKeysMenu.isOpen = true; isOpen = false; keyListener.edit = true }) {
                        Text("Edit Keys")
                    }
                    Button(onClick = { settingsMenu.isOpen = true; isOpen = false }) {
                        Text("Settings")
                    }
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
                    Button(onClick = { homeMenuOpen = true; selectModeOpen = false }) {
                        Text("Back")
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