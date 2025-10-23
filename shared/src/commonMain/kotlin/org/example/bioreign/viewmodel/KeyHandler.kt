package org.example.bioreign.viewmodel

import androidx.compose.ui.input.key.*
import io.github.compose_keyhandler.KeyHandler

val keyHandler = KeyHandler()

fun KeyHandler.setupPlayer(player: CharacterViewModel) {
    onPress {
        key(Key.A, Key.DirectionLeft) {
            player.move(-1F, 0F)
            println("LEFT")
        }
        key(Key.D, Key.DirectionRight) {
            player.move(1F, 0F)
            println("RIGHT")
        }
        key(Key.W, Key.DirectionUp) {
            player.move(0F, -1F)
            println("UP")
        }
        key(Key.S, Key.DirectionDown) {
            player.move(0F, 1F)
            println("DOWN")
        }
        key(Key.ShiftLeft, Key.ShiftRight) {
            player.sprint()
            println("SPRINT")
        }
        key(Key.F, Key.Spacebar) {
            player.attack()
            println("ATTACK")
        }
        key(Key.E, Key.E) {
            player.castSpell()
            println("CAST SPELL")
        }
        key(Key.R, Key.R) {
            player.cycleSpell()
            println("CYCLE SPELL")
        }
        key(Key.Q, Key.Q) {
            player.uniqueSkill()
            println("UNIQUE SKILL")
        }
        /*
        key(Key.Escape) {
            gameMenu.isOpen = !gameMenu.isOpen
        }
         */
    }
    onRelease {
        key(Key.A, Key.DirectionLeft) {
            player.move(1F, 0F)
        }
        key(Key.D, Key.DirectionRight) {
            player.move(-1F, 0F)
        }
        key(Key.W, Key.DirectionUp) {
            player.move(0F, 1F)
        }
        key(Key.S, Key.DirectionDown) {
            player.move(0F, -1F)
        }
        key(Key.ShiftLeft, Key.ShiftRight) {
            player.stopSprint()
            println("STOP SPRINT")
        }
    }
}