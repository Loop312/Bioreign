package org.example.bioreign.menus

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import org.example.bioreign.frameRate

class SettingsMenu {
    @Composable
    fun open(navBack: () -> Unit) {
        var fpstext by remember { mutableStateOf("") }
        Box {
            Column {
                Text("Settings")
                Row {
                    Text("FPS: ")
                    TextField(fpstext, onValueChange = {
                        try{
                        fpstext = it; frameRate = 1000/(it.toDouble())
                        } catch (e: Exception) {
                            println(e)
                        }
                    })
                }
                Button(onClick = { navBack() }) {
                    Text("Back")
                }
            }
        }
    }
}