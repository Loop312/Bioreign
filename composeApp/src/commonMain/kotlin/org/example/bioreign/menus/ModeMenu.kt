package org.example.bioreign.menus

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.width
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

class ModeMenu {
    @Composable
    fun open(navStory: () -> Unit, navPvP: () -> Unit, navRogue: () -> Unit, navBack: () -> Unit) {
        Box(Modifier.fillMaxSize()) {
            Row(Modifier.align(Alignment.Center)) {
                Button(onClick = { navStory() }) {
                    Text("Story Mode")
                }
                Spacer(Modifier.width(10.dp))

                Button(onClick = { navPvP() }) {
                    Text("PvP Mode")
                }
                Spacer(Modifier.width(10.dp))

                Button(onClick = { navRogue() }) {
                    Text("Roguelike Mode")
                }
            }
            Button(onClick = { navBack() },Modifier.align(Alignment.BottomCenter)) {
                Text("Back")
            }
        }
    }

}