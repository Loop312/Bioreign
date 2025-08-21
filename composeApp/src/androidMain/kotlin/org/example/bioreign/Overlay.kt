package org.example.bioreign

import androidx.compose.foundation.Image
import androidx.compose.foundation.gestures.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.hapticfeedback.HapticFeedbackType
import androidx.compose.ui.input.pointer.PointerInputChange
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalHapticFeedback
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.delay

class Overlay {
    var dx by mutableStateOf(0F)
    var dy by mutableStateOf(0F)
    var isOpen by mutableStateOf(true)
    var joystickType by mutableStateOf(true)

    //delete after integrating with gameloop
    //60 fps is the default
    var frameRate by mutableStateOf(1000/60.0)
    //if 120 fps this needs to be 1/2, if 240 fps this needs to be 1/4, if 30 fps this needs to be 2
    var frameRateMultiplier = 1.0

    @Composable
    fun open() {
        Box (Modifier.fillMaxSize()){
            if (isOpen) {
                if (joystickType) {
                    joyStick()
                } else {
                    joyStick2()
                }
                Box(Modifier.align(Alignment.BottomEnd).offset(-100.dp, -100.dp)) {
                    buttons()
                }
            }
            Column (Modifier.align(Alignment.BottomEnd)) {
                Button(onClick = { joystickType = !joystickType }) {
                    Text("change stick type")
                }
                Button(onClick = { isOpen = !isOpen }) {
                    Text("Toggle Overlay")
                }
            }
        }
    }

    @Composable
    fun joyStick() {
        val hapticFeedback = LocalHapticFeedback.current
        Box(Modifier.fillMaxSize()) {
            Box(Modifier.offset(10.dp, (-10).dp).size(100.dp).align(Alignment.BottomStart)) {
                Image(
                    painterResource(R.drawable.big_stick),
                    "Stick Background",
                    Modifier.alpha(.5F).size(100.dp).align(Alignment.BottomStart)
                )
                Image(
                    painterResource(R.drawable.small_stick2),
                    "Stick",
                    Modifier
                        .alpha(.5F)
                        .size(100.dp)
                        .align(Alignment.Center)
                        .offset(dx.dp, dy.dp)
                        .pointerInput(Unit) {
                            detectDragGestures(
                                onDragStart = { hapticFeedback.performHapticFeedback(HapticFeedbackType.LongPress)},
                                onDragEnd = { hapticFeedback.performHapticFeedback(HapticFeedbackType.LongPress)
                                    dx = 0F; dy = 0F},
                                onDrag = moveStick
                            )
                        }
                )
                Text("dx: $dx, dy: $dy")
            }
        }
        movePlayer()
    }

    @Composable
    fun joyStick2() {
        val touchPosition = remember { mutableStateOf(Offset.Zero) }
        var see by remember { mutableStateOf(false) }
        val hapticFeedback = LocalHapticFeedback.current
        Box (Modifier
            .fillMaxSize()
            .pointerInput(Unit) {
                detectDragGestures(
                    onDragStart = { change ->
                        see = true
                        touchPosition.value = Offset(change.x, change.y)
                        hapticFeedback.performHapticFeedback(HapticFeedbackType.LongPress)
                    },
                    onDragEnd = {
                        see = false; dx = 0F; dy = 0F
                        hapticFeedback.performHapticFeedback(HapticFeedbackType.LongPress)
                    },
                    onDrag = moveStick
                )
            }
        ) {
            Box(Modifier.offset { IntOffset(touchPosition.value.x.toInt(), touchPosition.value.y.toInt()) }) {
                if (see) {
                    Image(
                        painter = painterResource(id = R.drawable.big_stick),
                        contentDescription = null,
                        modifier = Modifier
                            .size(100.dp)
                            .offset((-50).dp, (-50).dp)
                            .alpha(.5F)
                    )
                    Image(
                        painterResource(R.drawable.small_stick2),
                        "Stick",
                        Modifier
                            .alpha(.5F)
                            .size(100.dp)
                            .offset((dx - 50).dp, (dy - 50).dp)
                    )
                }
                Text("dx: $dx, dy: $dy")
            }
        }
        movePlayer()
    }
    //add things to this so it can recognize direction changes, and collisions
    val moveStick = { _: PointerInputChange, dragAmount: Offset ->
        if (dx + dragAmount.x < -100F || dx + dragAmount.x > 100F) {
            dx += 0F
        } else {
            dx = (dx + dragAmount.x).coerceIn(-100F, 100F)
        }
        if (dy + dragAmount.y < -100F || dy + dragAmount.y > 100F) {
            dy += 0F
        } else {
            dy = (dy + dragAmount.y).coerceIn(-100F, 100F)
        }
    }
    @Composable
    fun movePlayer(){
        LaunchedEffect(Unit) {
            while (true) {
                delay(frameRate.toLong())
                if (dx >= 75 || dx <= -75 || dy >= 75 || dy <= -75) {
                    player.sprinting = if (player.stamina > 0) {true} else false
                    player.move((dx / 175) * frameRateMultiplier.toFloat(), (dy / 175) * frameRateMultiplier.toFloat())
                }
                else {
                    player.sprinting = false
                    player.move((dx / 100) * frameRateMultiplier.toFloat(), (dy / 100) * frameRateMultiplier.toFloat())
                }
            }
        }
    }

    @Composable
    fun buttons() {
        val hapticFeedback = LocalHapticFeedback.current
        Button(onClick = {player.castSpell(); hapticFeedback.performHapticFeedback(HapticFeedbackType.LongPress)},
            shape = CircleShape, modifier = Modifier.size(50.dp).offset(80.dp, -40.dp)) {
            Text("B")
        }
        Button(onClick = {player.cycleSpell(); hapticFeedback.performHapticFeedback(HapticFeedbackType.LongPress)},
            shape = CircleShape, modifier = Modifier.size(50.dp).offset(40.dp, -80.dp)) {
            Text("Y")
        }
        Button(onClick = {player.uniqueSkill(); hapticFeedback.performHapticFeedback(HapticFeedbackType.LongPress)},
            shape = CircleShape, modifier = Modifier.size(50.dp).offset(40.dp, 0.dp)) {
            Text("A")
        }
        Button(onClick = {player.attack(); hapticFeedback.performHapticFeedback(HapticFeedbackType.LongPress)},
            shape = CircleShape, modifier = Modifier.size(50.dp).offset(0.dp, -40.dp)) {
            Text("X")
        }
    }
}