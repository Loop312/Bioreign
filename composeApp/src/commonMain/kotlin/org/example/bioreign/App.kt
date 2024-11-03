package org.example.bioreign

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.key.*
import androidx.compose.ui.unit.dp
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.ui.tooling.preview.Preview
import bioreign.composeapp.generated.resources.*
import characters.Player
import org.example.bioreign.maps.Map
import menus.EditKeysMenu
import menus.InGameMenu
import menus.PregameMenu


val player = Player()
val gameLoop = GameLoop()
val keyListener = KeyListener()
val map = Map(Res.drawable.tempmap)
val hud = HUD()
val gameMenu = InGameMenu()
val pregameMenu = PregameMenu()
val editKeysMenu = EditKeysMenu()

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
            Image(
                painter = painterResource(Res.drawable.compose_multiplatform),
                contentDescription = "lol",
                modifier = Modifier
                    .offset(player.x.dp, player.y.dp)
                    .size(100.dp)
                    .align(Alignment.Center)
            )
            Column {
                hud.healthBar()
                hud.staminaBar()
                hud.expBar()
                Text("Player Location: (${map.x}, ${map.y})")
            }
            gameMenu.open()
        }
    }
}

@Composable
@Preview
fun theApp() {
    pregameMenu.open()
    editKeysMenu.open()
    keyListener.listen()
    if (gameLoop.isPlaying) {
        game()
        gameLoop.GameScreen()
        gameLoop.playerStuff()
        map.checkCollisions()
    }
    Text(keyListener.pressedKeys.toString())
    //there's an issue with the focus after clicking the "Start Game"
    //button in PregameMenu, this button is a temporary workaround
    Button(onClick = {},Modifier.onKeyEvent(keyListener.listener)){}
}