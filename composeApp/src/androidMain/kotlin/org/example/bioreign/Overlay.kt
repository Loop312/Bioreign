package org.example.bioreign

import androidx.compose.foundation.ExperimentalFoundationApi
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
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalHapticFeedback
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.delay

@OptIn(ExperimentalFoundationApi::class)
class Overlay {
    var dx by mutableStateOf(0F)
    var dy by mutableStateOf(0F)
    var isOpen by mutableStateOf(true)
    var joysticktype by mutableStateOf(true)

    @Composable
    fun open() {
        Box (Modifier.fillMaxSize()){
            if (isOpen) {
                if (joysticktype) {
                    joyStick()
                } else {
                    joyStick2()
                }
                Box(Modifier.align(Alignment.BottomEnd).offset(-100.dp, -100.dp)) {
                    buttons()
                }
            }
            Column (Modifier.align(Alignment.BottomEnd)) {
                Button(onClick = { joysticktype = !joysticktype }) {
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
                        .draggable2D(
                            state = rememberDraggable2DState { onDelta ->
                                if (dx + onDelta.x < -100F || dx + onDelta.x > 100F) {
                                    dx += 0F
                                } else {
                                    dx = (dx + onDelta.x).coerceIn(-100F, 100F)
                                }
                                if (dy + onDelta.y < -100F || dy + onDelta.y > 100F) {
                                    dy += 0F
                                } else {
                                    dy = (dy + onDelta.y).coerceIn(-100F, 100F)
                                }
                            },
                            onDragStopped = {hapticFeedback.performHapticFeedback(HapticFeedbackType.LongPress)
                                dx = 0F; dy = 0F
                                            },
                            onDragStarted = {hapticFeedback.performHapticFeedback(HapticFeedbackType.LongPress)}
                        )
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
        Box (Modifier.fillMaxSize().pointerInput(Unit) {
            detectDragGestures(onDragStart = { change ->
                see = true
                touchPosition.value = Offset(change.x, change.y)
                hapticFeedback.performHapticFeedback(HapticFeedbackType.LongPress)
            }, onDragEnd = {
                see = false; dx = 0F; dy = 0F
                hapticFeedback.performHapticFeedback(HapticFeedbackType.LongPress)
            }) {change, dragOffset -> //same method as joystick1, need to update both when completely fixed
                if (dx + dragOffset.x < -100F || dx + dragOffset.x > 100F) {
                    dx += 0F
                } else {
                    dx = (dx + dragOffset.x).coerceIn(-100F, 100F)
                }
                if (dy + dragOffset.y < -100F || dy + dragOffset.y > 100F) {
                    dy += 0F
                } else {
                    dy = (dy + dragOffset.y).coerceIn(-100F, 100F)
                }
            }
        }) {
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

    @Composable
    fun movePlayer(){
        LaunchedEffect(Unit) {
            while (true) {
                delay(1000/60)
                if (dx >= 60 || dx <= -60 || dy >= 60 || dy <= -60) {
                    player.sprinting = true
                    player.move(dx / 175, dy / 175)
                }
                else {
                    player.sprinting = false
                    player.move(dx / 100, dy / 100)
                }
            }
        }
    }

    @Composable
    fun buttons() {
        val hapticFeedback = LocalHapticFeedback.current
        Button(onClick = {hapticFeedback.performHapticFeedback(HapticFeedbackType.LongPress)},
            shape = CircleShape, modifier = Modifier.size(50.dp).offset(80.dp, -40.dp)) {
            Text("B")
        }
        Button(onClick = {hapticFeedback.performHapticFeedback(HapticFeedbackType.LongPress)},
            shape = CircleShape, modifier = Modifier.size(50.dp).offset(40.dp, -80.dp)) {
            Text("Y")
        }
        Button(onClick = {hapticFeedback.performHapticFeedback(HapticFeedbackType.LongPress)},
            shape = CircleShape, modifier = Modifier.size(50.dp).offset(40.dp, 0.dp)) {
            Text("A")
        }
        Button(onClick = {hapticFeedback.performHapticFeedback(HapticFeedbackType.LongPress)},
            shape = CircleShape, modifier = Modifier.size(50.dp).offset(0.dp, -40.dp)) {
            Text("X")
        }
    }
}