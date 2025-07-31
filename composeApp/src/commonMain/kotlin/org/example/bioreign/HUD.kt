package org.example.bioreign

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.*
import androidx.compose.ui.unit.*

class HUD {

    @Composable
    fun display(fps: Float, frameTime: Float){
        Column (Modifier.background(Color.LightGray)) {
            hud.healthBar()
            hud.staminaBar()
            hud.manaBar()
            hud.expBar()
            Text("Player Location: (${map.x}, ${map.y})\n" +
                    "Player Spells: " + player.spells.joinToString { it.name } + "\n" +
                    "Player Current Spell: ${player.spells[player.currentSpell].name}",
                color = Color.Magenta
            )
            Text("FPS: $fps \n FrameTime: $frameTime ms")
            Text("HORIZONTAL VELOCITY: ${player.horizontalVelocity}\n" +
                    "VERTICAL VELOCITY: ${player.verticalVelocity}"
            )
        }
    }
    @Composable
    fun healthBar(){
        Column {
            Text(
                "HP: ${player.hp}/${player.maxHp}",
                color = Color.Green
            )
            Box(
                modifier = Modifier
                    .height(30.dp)
                    .width((player.maxHp * 10).dp)
                    .border(width = 2.dp, color = Color.Gray)
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxHeight()
                        .width((player.hp * 10).dp)
                        .background(Color.Green)
                )
            }
        }
    }

    @Composable
    fun staminaBar(){
        Column {
            Text(
                "Stamina: ${player.stamina.toInt()}/${player.maxStamina.toInt()}",
                color = Color.Yellow
            )
            Box(
                modifier = Modifier
                    .height(20.dp)
                    .width((player.maxStamina * 10).dp)
                    .border(width = 2.dp, color = Color.Gray)
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxHeight()
                        .width((player.stamina * 10).dp)
                        .background(Color.Yellow)
                )
            }
        }
    }

    @Composable
    fun manaBar(){
        Column {
            Text(
                "Mana: ${player.mana.toInt()}/${player.maxMana.toInt()}",
                color = Color.Cyan
            )
            Box(
                modifier = Modifier
                    .height(20.dp)
                    .width((player.maxMana * 10).dp)
                    .border(width = 2.dp, color = Color.Gray)
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxHeight()
                        .width((player.mana * 10).dp)
                        .background(Color.Cyan)
                )
            }
        }
    }
    @Composable
    fun expBar() {
        Column {
            Text(
                player.getExp(),
                color = Color.Blue
            )
            Box(
                modifier = Modifier
                    .height(10.dp)
                    .width(100.dp)
                    .border(width = 2.dp, color = Color.Gray)
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxHeight()
                        .width((player.exp / player.expLimit * 100).dp)
                        .background(Color.Blue)
                )
            }
            Text(
                "Player lvl: " + player.lvl + "\nSkill Points: " + player.skillPoints,
                color = Color.Blue
            )
        }
    }
}