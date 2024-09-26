package menus

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.input.key.Key
import keyListener
import pregameMenu

class EditKeysMenu: Menu() {
    //needs changes to how it's setup and the way it calls the onClick function
    @Composable
    override fun open() {
        if (isOpen) {
            Column {
                for (i in 0..<keyListener.keys.size) {
                    Row {
                        Button(onClick = { keyListener.editKeybinds(i, Key.A) }) {
                            Text(keyListener.keys[i].toString())
                        }
                    }
                }
                Button(onClick = {pregameMenu.isOpen = true; isOpen = false}) {
                    Text("Back to Home")
                }
            }
        }
    }
}