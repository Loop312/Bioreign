package org.example.bioreign

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.*
import androidx.compose.ui.graphics.*
import androidx.compose.ui.unit.*

class HUD {

    @Composable
    fun display(){
        Column (Modifier.background(Color.LightGray)) {
            hud.healthBar()
            hud.staminaBar()
            hud.expBar()
            Text("Player Location: (${map.x}, ${map.y})")
        }
    }
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
    @Composable
    fun expBar(){
        Box(modifier = Modifier
            .height(20.dp)
            .width(100.dp)
            .padding(top = 10.dp)
            .border(width = 2.dp, color = Color.Gray)
        ){
            Box(
                modifier = Modifier
                    .fillMaxHeight()
                    .width((player.exp/player.explimit*100).dp)
                    .background(Color.Blue)
            )
        }
        Text(player.getExp() + "Player lvl: " + player.lvl + "\nSkill Points: " + player.skillPoints, color = Color.Blue)
    }
}