package org.example.bioreign

import androidx.compose.runtime.*
import androidx.compose.ui.input.key.*

class KeyListener () {
    var pressedKeys by mutableStateOf<Set<Key>>(emptySet())
    var edit = true
    var keys by mutableStateOf(
        arrayOf(
        Key.A, Key.DirectionLeft,       //Left: 0,1
        Key.D, Key.DirectionRight,      //Right: 2,3
        Key.W, Key.DirectionUp,         //Up: 4,5
        Key.S, Key.DirectionDown,       //Down: 6,7
        Key.ShiftLeft, Key.ShiftRight   //Sprint: 8,9
        )
    )
    val temp = arrayOf("LEFT", "RIGHT", "UP", "DOWN", "SPRINT")

    var esc = true

    fun listen() {
        if (!edit) {
            //MOVEMENT
            if (keys[0] in pressedKeys || keys[1] in pressedKeys) {
                if (map.canMoveLeft) {
                    player.move(-1, 0)
                }
            }
            if (keys[2] in pressedKeys || keys[3] in pressedKeys) {
                if (map.canMoveRight) {
                    player.move(1, 0)
                }
            }
            if (keys[4] in pressedKeys || keys[5] in pressedKeys) {
                if (map.canMoveUp) {
                    player.move(0, -1)
                }
            }
            if (keys[6] in pressedKeys || keys[7] in pressedKeys) {
                if (map.canMoveDown) {
                    player.move(0, 1)
                }
            }
            if (keys[8] in pressedKeys || keys[9] in pressedKeys && player.stamina > 0) {
                player.sprinting = true
                player.stamina -= 0.1
            } else {
                player.sprinting = false
            }

            //ATTACK AND SPELLS
        }
        //access menu
        if (Key.Escape in pressedKeys && esc) {
            gameMenu.isOpen = !gameMenu.isOpen
            println("Toggle menu")
            esc = false
        }
    }

    //create a function for handling keybind changes
    fun editKeybinds(original: Int, newKey: Key){
        keys[original] = newKey
    }

    val listener = { event: KeyEvent ->
        //when a button is pressed add it to pressedKeys
        when (event.type) {
            KeyEventType.KeyDown -> {
                keyListener.pressedKeys += event.key
            }

            KeyEventType.KeyUp -> {
                keyListener.pressedKeys -= event.key
            }
        }
        true
    }
}