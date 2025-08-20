package org.example.bioreign.ui.components

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.*
import androidx.compose.ui.unit.*
import org.example.bioreign.model.CharacterState
import org.example.bioreign.player


@Composable
fun DisplayHUD(state: CharacterState) {
    val stats = state.stats
    Column (Modifier.background(Color.LightGray)) {
        HealthBar(stats.hp, stats.maxHp)
        StaminaBar(stats.stamina, stats.maxStamina)
        ManaBar(stats.mana, stats.maxMana)
        ExpBar(stats.exp, stats.expLimit)
        Text(
            "Player lvl: " + player.lvl + "\nSkill Points: " + player.skillPoints,
            color = Color.Blue
        )
        Text("Player Location: ${state.position}")

        if (state.spells.isNotEmpty()) {
            Text("Player Spells: " + state.spells.joinToString { it.name } + "\n" +
                    "Player Current Spell: ${state.spells[state.currentSpell].name}",
                )
        } else {
            Text("Player has no spells")
        }
        //Text("FPS: $fps \n FrameTime: $frameTime ms")
        Text("HORIZONTAL VELOCITY: ${state.horizontalVelocity}\n" +
                "VERTICAL VELOCITY: ${state.verticalVelocity}"
        )
    }
}
@Composable
private fun HealthBar(hp: Int, maxHp: Int){
    Column {
        Text(
            "HP: $hp/$maxHp",
            color = Color.Green
        )
        Box(
            modifier = Modifier
                .height(30.dp)
                .width((maxHp * 10).dp)
                .border(width = 2.dp, color = Color.Gray)
        ) {
            Box(
                modifier = Modifier
                    .fillMaxHeight()
                    .width((hp * 10).dp)
                    .background(Color.Green)
            )
        }
    }
}

@Composable
fun StaminaBar(stamina: Double, maxStamina: Double){
    Column {
        Text(
            "Stamina: ${stamina.toInt()}/${maxStamina.toInt()}",
            color = Color.Yellow
        )
        Box(
            modifier = Modifier
                .height(20.dp)
                .width((maxStamina * 10).dp)
                .border(width = 2.dp, color = Color.Gray)
        ) {
            Box(
                modifier = Modifier
                    .fillMaxHeight()
                    .width((stamina * 10).dp)
                    .background(Color.Yellow)
            )
        }
    }
}

@Composable
fun ManaBar(mana: Double, maxMana: Double){
    Column {
        Text(
            "Mana: ${mana.toInt()}/${maxMana.toInt()}",
            color = Color.Cyan
        )
        Box(
            modifier = Modifier
                .height(20.dp)
                .width((maxMana * 10).dp)
                .border(width = 2.dp, color = Color.Gray)
        ) {
            Box(
                modifier = Modifier
                    .fillMaxHeight()
                    .width((mana * 10).dp)
                    .background(Color.Cyan)
            )
        }
    }
}
@Composable
fun ExpBar(exp: Float, expLimit: Float) {
    Column {
        Text(
            "EXP: $exp/$expLimit",
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
                    .width((exp / expLimit * 100).dp)
                    .background(Color.Blue)
            )
        }
    }
}
