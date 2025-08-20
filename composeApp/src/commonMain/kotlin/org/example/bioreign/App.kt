package org.example.bioreign

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
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
fun Bioreign() {
    val nav = remember { Nav() }
    //there's an issue with the focus after clicking the "Start Game"
    //button in PregameMenu, this box is a temporary workaround
    Box(Modifier
        .fillMaxSize()
        .background(Color.DarkGray)
    ){
        nav.Activate()
        //keyListener.listen()
        Column {
            Text(keyHandler.pressedKeys.toString())
        }
        //not really needed anymore
    }
}