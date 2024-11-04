package org.example.bioreign

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.gestures.*
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.input.pointer.pointerInput
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
                            onDragStopped = { dx = 0F; dy = 0F },
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
        Box (Modifier.fillMaxSize().pointerInput(Unit) {
            detectDragGestures(onDragStart = { change ->
                touchPosition.value = Offset(change.x, change.y)
            }){change, dragOffset ->
                //touchPosition.value = Offset(change.position.x, change.position.y)
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
                Image(
                    painter = painterResource(id = R.drawable.big_stick),
                    contentDescription = null,
                    modifier = Modifier
                        .size(100.dp)
                        .offset(-50.dp, -50.dp)
                        .alpha(.5F)
                )
                Image(
                    painterResource(R.drawable.small_stick2),
                    "Stick",
                    Modifier
                        .alpha(.5F)
                        .size(100.dp)
                        //.align(Alignment.Center)
                        .offset((dx-50).dp, (dy-50).dp)
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
                            onDragStopped = { dx = 0F; dy = 0F },
                        )
                )
                Text("dx: $dx, dy: $dy")
            }
        }
    }

    @Composable
    fun movePlayer(){
        LaunchedEffect(Unit) {
            while (true) {
                delay(1000/60)
                player.move(dx/100, dy/100)
            }
        }
    }
}