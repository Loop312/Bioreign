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
import androidx.compose.ui.unit.dp
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.ui.tooling.preview.Preview

import bioreign.composeapp.generated.resources.Res
import bioreign.composeapp.generated.resources.compose_multiplatform
import characters.Player
import maps.Map
import menus.EditKeysMenu
import menus.InGameMenu
import menus.PregameMenu


val player = Player()
val gameLoop = GameLoop()
val keyListener = KeyListener()
val map = Map(Res.drawable.compose_multiplatform)
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
    //open the pregame menu and wait to start the game
    if (gameLoop.isplaying) {
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
    if (gameLoop.isplaying) {
        game()
        gameLoop.GameScreen()
        map.checkCollisions()
    }
}