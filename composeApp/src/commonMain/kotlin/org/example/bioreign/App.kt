package org.example.bioreign

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.ui.tooling.preview.Preview
import bioreign.composeapp.generated.resources.*
//bioreign imports
import org.example.bioreign.characters.Player
import org.example.bioreign.maps.Map
import org.example.bioreign.menus.Nav


val player = Player()
val keyListener = KeyListener()
val map = Map(Res.drawable.tempmap)

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

/*
copied and pasted from windows
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
*/

@Composable
@Preview
fun theApp() {
    val nav = remember { Nav() }
    //there's an issue with the focus after clicking the "Start Game"
    //button in PregameMenu, this box is a temporary workaround
    Box(Modifier
        .fillMaxSize()
        .background(Color.DarkGray)
    ){
        nav.activate()
        //keyListener.listen()
        Column {
            Text(keyHandler.pressedKeys.toString())
        }
        //not really needed anymore
    }
}