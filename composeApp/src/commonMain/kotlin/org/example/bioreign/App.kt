package org.example.bioreign

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import org.jetbrains.compose.ui.tooling.preview.Preview
import org.example.bioreign.menus.Nav

@Composable
@Preview
fun Bioreign() {
    val nav = remember { Nav() }
    //there's an issue with the focus after clicking the "Start Game"
    //button in PregameMenu, this box is a temporary workaround
    MaterialTheme {
        Box(
            Modifier
                .fillMaxSize()
                .background(Color.Black)
        ) {
            nav.Activate()
        }
    }
}