package org.example.bioreign.menus

import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import org.example.bioreign.player

class InGameMenu: Menu() {
    @Composable
    override fun open(){
        if (isOpen) {
            Box {
                Text(player.displayStats())
            }
        }
    }
}