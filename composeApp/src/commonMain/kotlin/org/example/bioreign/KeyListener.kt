package org.example.bioreign

import androidx.compose.runtime.*
import androidx.compose.ui.input.key.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class KeyListener {
    var pressedKeys by mutableStateOf<Set<Key>>(emptySet())
    var edit = true
    var keys by mutableStateOf(
        arrayOf(
            Key.A, Key.DirectionLeft,       //Left: 0,1
            Key.D, Key.DirectionRight,      //Right: 2,3
            Key.W, Key.DirectionUp,         //Up: 4,5
            Key.S, Key.DirectionDown,       //Down: 6,7
            Key.ShiftLeft, Key.ShiftRight,   //Sprint: 8,9
            Key.Spacebar, Key.Spacebar,     //Attack: 10,11
            Key.E, Key.E,                   //Cast Spell: 12,13
            Key.R, Key.R,                   //Cycle Spell: 14,15
            Key.Q, Key.Q,                   //Unique Skill: 16,17
        )
    )
    val keyNames = arrayOf("LEFT", "RIGHT", "UP", "DOWN", "SPRINT", "ATTACK", "CAST", "CYCLE", "UNIQUE")

    var esc = true
    @Composable
    fun listen() {
        if (!edit) {
            //MOVEMENT
            if (keys[0] in pressedKeys || keys[1] in pressedKeys) {
                //possible reordering needed for true/false setting
                player.movingLeft = true
                player.movingRight = false
                if (map.canMoveLeft) {
                    player.move(-1F, 0F)
                }
            }
            if (keys[2] in pressedKeys || keys[3] in pressedKeys) {
                player.movingLeft = false
                player.movingRight = true
                if (map.canMoveRight) {
                    player.move(1F, 0F)
                }
            }
            if (keys[4] in pressedKeys || keys[5] in pressedKeys) {
                player.movingUp = true
                player.movingDown = false
                if (map.canMoveUp) {
                    player.move(0F, -1F)
                }
            }
            if (keys[6] in pressedKeys || keys[7] in pressedKeys) {
                player.movingUp = false
                player.movingDown = true
                if (map.canMoveDown) {
                    player.move(0F, 1F)
                }
            }
            if (keys[8] in pressedKeys || keys[9] in pressedKeys) {
                player.sprinting = if (player.stamina > 0) {true} else false
            } else {
                player.sprinting = false
            }

            //ATTACK AND SPELLS
            if (keys[10] in pressedKeys || keys[11] in pressedKeys) {
                player.attack()
            }
            if (keys[12] in pressedKeys || keys[13] in pressedKeys) {
                player.castSpell()
            }
            if (keys[14] in pressedKeys || keys[15] in pressedKeys) {
                player.cycleSpell()
            }
            if (keys[16] in pressedKeys || keys[17] in pressedKeys) {
                player.uniqueSkill()
            }

        }
        //access menu
        if (Key.Escape in pressedKeys && esc) {
            gameMenu.isOpen = !gameMenu.isOpen
            println("Toggle menu")
            esc = false
        }
    }

    //create a function for handling keybind changes
    fun editKeybinds(original: Int){
        CoroutineScope(Dispatchers.Default).launch {
            if (pressedKeys.isNotEmpty()) {
                keys[original] = pressedKeys.first()
            }
            else {
                while (pressedKeys.isEmpty()) {
                    delay(100)
                    if (pressedKeys.isNotEmpty()) {
                        keys[original] = pressedKeys.first()
                        break
                    }
                }
            }
        }
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