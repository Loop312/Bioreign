package org.example.bioreign.menus

import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import org.example.bioreign.player

class InGameMenu {
    @Composable
    fun open(){
        Box {
            Text(player.displayStats())
        }
    }
}