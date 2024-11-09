package org.example.bioreign

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.input.key.*
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.ui.tooling.preview.Preview
import bioreign.composeapp.generated.resources.*
import characters.Player
import org.example.bioreign.maps.Map
import menus.*
import org.example.bioreign.gamemodes.*


val player = Player()
val gameLoop = GameLoop()
val keyListener = KeyListener()
val map = Map(Res.drawable.tempmap)
val hud = HUD()
val gameMenu = InGameMenu()
val pregameMenu = PregameMenu()
val editKeysMenu = EditKeysMenu()
val storymode = Storymode()
val online = Online()
val rogue = Rogue()

@Composable
@Preview
fun App() {
    MaterialTheme {
        var showContent by remember { mutableStateOf(false) }
        Column(Modifier.fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally) {
            Button(onClick = { showContent = !showContent }) {
                Text("Click me!")
            }
            AnimatedVisibility(showContent) {
                val greeting = remember { Greeting().greet() }
                Column(Modifier.fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally) {
                    Image(painterResource(Res.drawable.compose_multiplatform), null)
                    Text("Compose: $greeting")
                }
            }
        }
    }
}


//copied and pasted from windows

@Composable
fun game(){
    if (gameLoop.isPlaying) {
        Box {
            map.load()
            player.load(Res.drawable.compose_multiplatform)
            hud.display()
            gameMenu.open()
        }
    }
}

@Composable
@Preview
fun theApp() {
    val focusRequester = remember {FocusRequester()}
    //there's an issue with the focus after clicking the "Start Game"
    //button in PregameMenu, this box is a temporary workaround
    Box(Modifier.fillMaxSize().focusRequester(focusRequester).clickable{focusRequester.requestFocus()}.onKeyEvent(keyListener.listener)) {
        pregameMenu.open()
        editKeysMenu.open()
        keyListener.listen()
        storymode.saveMenu()
        if (gameLoop.isPlaying) {
            game()
            gameLoop.GameScreen()
            gameLoop.playerStuff()
            map.checkCollisions()
        }
        Text(keyListener.pressedKeys.toString())
        //not really needed anymore
        //Button(onClick = {}, Modifier.onKeyEvent(keyListener.listener)) {}
    }
}