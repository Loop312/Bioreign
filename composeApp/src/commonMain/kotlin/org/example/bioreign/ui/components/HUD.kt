package org.example.bioreign.ui.components

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.*
import androidx.compose.ui.unit.*
import org.example.bioreign.model.CharacterState


@Composable
fun DisplayHUD(state: CharacterState, fps: Float, frameTime: Float) {
    val stats = state.stats
    Column (Modifier.background(Color.White.copy(alpha = 0.5f))) {
        Bar("HP", stats.hp, stats.maxHp, Color.Green, 30, stats.maxHp * 10)
        Bar("Stamina", stats.stamina, stats.maxStamina, Color.Yellow, 20, stats.maxStamina * 10)
        Bar("Mana", stats.mana, stats.maxMana, Color.Cyan, 20, stats.maxMana * 10)
        Bar("Exp", stats.exp, stats.expLimit, Color.Blue, 10, 100f)
        Text(
            "Player lvl: " + stats.lvl + "\nSkill Points: " + stats.skillPoints,
            color = Color.Blue
        )
        Text("Player Location: ${state.hitBox.topLeft}")
        Text("Player Tile Location: ${state.tileOffset}")

        if (state.spells.isNotEmpty()) {
            Text("Player Spells: " + state.spells.joinToString { it.name } + "\n" +
                    "Player Current Spell: ${state.spells[state.currentSpell].name}",
                )
        } else {
            Text("Player has no spells")
        }
        Text("FPS: ${fps.toInt()} \n FrameTime: $frameTime ms")
        Text("HORIZONTAL VELOCITY: ${state.horizontalVelocity}\n" +
                "VERTICAL VELOCITY: ${state.verticalVelocity}"
        )
    }
}

@Composable
fun Bar(
    statName: String,
    stat: Float,
    maxStat: Float,
    color: Color,
    height: Int,
    width: Float
) {
    Column {
        Text(
            "$statName: $stat/$maxStat",
            color = color
        )
        Box(
            modifier = Modifier
                .height(height.dp)
                .width(width.dp)
                .border(width = 2.dp, color = Color.Gray)
        ) {
            Box(
                modifier = Modifier
                    .fillMaxHeight()
                    .width((stat / maxStat * width).dp)
                    .background(color)
            )
        }
    }
}

@Composable
fun Bar(
    statName: String,
    stat: Int,
    maxStat: Int,
    color: Color,
    height: Int,
    width: Int
) {
    Column {
        Text(
            "$statName: $stat/$maxStat",
            color = color
        )
        Box(
            modifier = Modifier
                .height(height.dp)
                .width(width.dp)
                .border(width = 2.dp, color = Color.Gray)
        ) {
            Box(
                modifier = Modifier
                    .fillMaxHeight()
                    .width((stat / maxStat * width).dp)
                    .background(color)
            )
        }
    }
}