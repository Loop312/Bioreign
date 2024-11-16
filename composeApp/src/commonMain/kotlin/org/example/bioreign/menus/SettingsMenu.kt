package menus

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import org.example.bioreign.frameRate
import org.example.bioreign.pregameMenu

class SettingsMenu: Menu() {

    var fpstext by mutableStateOf("")
    @Composable
    override fun open() {
        if (isOpen) {
            Box {
                Column {
                    Text("Settings")
                    Row {

                        Text("FPS: ")
                        TextField(fpstext, onValueChange = {
                            try{
                            fpstext = it; frameRate = 1000/it.toDouble()
                            } catch (e: Exception) {
                                println(e)
                            }
                        })
                    }
                    Button(onClick = { pregameMenu.isOpen = true; isOpen = false }) {
                        Text("Back")
                    }
                }
            }
        }
    }
}