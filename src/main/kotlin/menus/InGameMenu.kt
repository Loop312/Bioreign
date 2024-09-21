package menus

import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import player

class InGameMenu {
    var isOpen by mutableStateOf(false)
    @Composable
    fun stats(){
        if (isOpen) {
            Box {
                Text(player.displayStats())
            }
        }
    }
}