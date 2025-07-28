package org.example.bioreign.menus

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import org.example.bioreign.keyListener


class EditKeysMenu {
    //needs changes to how it's setup and the way it calls the onClick function
    @Composable
    fun open(navBack: () -> Unit) {
        Column {
            for (i in 0..<keyListener.keys.size step 2) {
                Row {
                    Text(keyListener.keyNames[i/2], modifier = Modifier.padding(10.dp))
                    Button(onClick = { keyListener.editKeybinds(i); refresh()}) {
                        Text(keyListener.keys[i].toString())
                    }
                    Button(onClick = { keyListener.editKeybinds(i+1); refresh()}) {
                        Text(keyListener.keys[i+1].toString())
                    }
                }
            }
            Button(onClick = {navBack()}) {
                Text("Back to Home")
            }
        }
    }

    fun refresh() {

    }
}