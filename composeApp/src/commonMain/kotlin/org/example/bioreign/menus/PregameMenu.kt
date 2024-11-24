package org.example.bioreign.menus

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import bioreign.composeapp.generated.resources.BioreignTempLogo
import bioreign.composeapp.generated.resources.Res
import bioreign.composeapp.generated.resources.compose_multiplatform
import bioreign.composeapp.generated.resources.tempmap
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
            selectCharacter()
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
                    Button(onClick = { characterMenuOpen = true; selectModeOpen = false }) {
                        Text("PvP Mode")
                    }
                    Button(onClick = { rogue.startGame(); selectModeOpen = false }) {
                        Text("Roguelike Mode")
                    }
                }
                Button(onClick = { homeMenuOpen = true; selectModeOpen = false },Modifier.align(Alignment.BottomCenter)) {
                    Text("Back")
                }
            }
        }
    }

    private val characterImages = listOf(Res.drawable.compose_multiplatform, Res.drawable.BioreignTempLogo, Res.drawable.tempmap)
    @Composable
    fun selectCharacter() {
        var i by remember {  mutableStateOf(0) }
        if (characterMenuOpen) {
            Box(Modifier.fillMaxSize()) {
                Image(painterResource(characterImages[i]), null, Modifier
                    .align(Alignment.Center)
                    .size(200.dp)
//                                                                  //add check for mode
                    .clickable{ player.image = characterImages[i]; storymode.startGame(); characterMenuOpen = false}
                )
                //% characterImages.size makes it loop through and come back to the beginning
                Button({ i = (i + 1) % characterImages.size }, Modifier.align(Alignment.CenterEnd)) {
                    Text(">")
                }
                Button({ i = (i - 1 + characterImages.size) % characterImages.size }, Modifier.align(Alignment.CenterStart)) {
                    Text("<")
                }
                Button({ selectModeOpen = true; characterMenuOpen = false}, Modifier.align(Alignment.BottomCenter)) {
                    Text("Back")
                }
                Text ("Character Select: $i", Modifier.align(Alignment.TopCenter))
            }
        }
    }
}