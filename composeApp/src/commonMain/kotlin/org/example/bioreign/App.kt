package org.example.bioreign

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
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
val map = Map("maps/Placeholder.jpg")
val hud = HUD()
val gameMenu = InGameMenu()
val pregameMenu = PregameMenu()
val editKeysMenu = EditKeysMenu()
val debugger = DebugWindow()

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