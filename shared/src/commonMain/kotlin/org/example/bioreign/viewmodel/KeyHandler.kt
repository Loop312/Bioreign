package org.example.bioreign.viewmodel

import androidx.compose.ui.input.key.*
import io.github.compose_keyhandler.KeyHandler

val keyHandler = KeyHandler()

fun KeyHandler.setupPlayer(player: CharacterViewModel) {
    addMultipleSingleActionKeys(setOf(Key.A, Key.DirectionLeft), "LEFT") {
        player.move(-10F, 0F)
        println("LEFT")
    }
    addMultipleReleaseKeys(setOf(Key.A, Key.DirectionLeft), "LEFT") {
        player.move(10F, 0F)
    }
    addMultipleSingleActionKeys(setOf(Key.D, Key.DirectionRight), "RIGHT") {
        player.move(10F, 0F)
        println("RIGHT")
    }
    addMultipleReleaseKeys(setOf(Key.D, Key.DirectionRight), "RIGHT") {
        player.move(-10F, 0F)
    }
    addMultipleSingleActionKeys(setOf(Key.W, Key.DirectionUp), "UP") {
        player.move(0F, -10F)
        println("UP")
    }
    addMultipleReleaseKeys(setOf(Key.W, Key.DirectionUp), "UP") {
        player.move(0F, 10F)
    }
    addMultipleSingleActionKeys(setOf(Key.S, Key.DirectionDown), "DOWN") {
        player.move(0F, 10F)
        println("DOWN")
    }
    addMultipleReleaseKeys(setOf(Key.S, Key.DirectionDown), "DOWN") {
        player.move(0F, -10F)
    }

    addMultipleKeys(setOf(Key.ShiftLeft, Key.ShiftRight), "SPRINT") {
        player.sprint()
        println("SPRINT")
    }
    addMultipleReleaseKeys(setOf(Key.ShiftLeft, Key.ShiftRight), "STOP SPRINT") {
        player.stopSprint()
        println("STOP SPRINT")
    }

    addMultipleKeys(setOf(Key.F, Key.Spacebar), "ATTACK") {
        player.attack()
        println("ATTACK")
    }
    addMultipleKeys(setOf(Key.E, Key.E), "CAST SPELL") {
        player.castSpell()
        println("CAST SPELL")
    }
    addMultipleKeys(setOf(Key.R, Key.R), "CYCLE SPELL") {
        player.cycleSpell()
        println("CYCLE SPELL")
    }
    addMultipleKeys(setOf(Key.Q, Key.Q), "UNIQUE SKILL") {
        player.uniqueSkill()
        println("UNIQUE SKILL")
    }
    /*
    addSingleActionKey(Key.Escape, "ESC") {
        gameMenu.isOpen = !gameMenu.isOpen
    }
     */
}