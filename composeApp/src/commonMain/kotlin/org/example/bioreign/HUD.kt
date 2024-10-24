package org.example.bioreign

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.*
import androidx.compose.ui.*
import androidx.compose.ui.graphics.*
import androidx.compose.ui.unit.*
import org.example.bioreign.player

class HUD {
    @Composable
    fun healthBar(){
        Box(modifier = Modifier
            .height(40.dp)
            .width((player.maxHp*10).dp)
            .padding(top = 10.dp)
            .border(width = 2.dp, color = Color.Gray)
        ){
            Box(
                modifier = Modifier
                    .fillMaxHeight()
                    .width((player.hp*10).dp)
                    .background(Color.Green)
            )
        }
    }

    @Composable
    fun staminaBar(){
        Box(modifier = Modifier
            .height(20.dp)
            .width((player.maxStamina*10).dp)
            .padding(top = 10.dp)
            .border(width = 2.dp, color = Color.Gray)
        ){
            Box(
                modifier = Modifier
                    .fillMaxHeight()
                    .width((player.stamina*10).dp)
                    .background(Color.Yellow)
            )
        }
    }
}