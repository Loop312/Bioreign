package org.example.bioreign

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.gestures.*
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.size
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.delay

class Overlay {

    @Composable
    fun open() {
        joyStick()
    }
    @OptIn(ExperimentalFoundationApi::class)
    @Composable
    fun joyStick() {
        var dx by remember {mutableStateOf(0F)}
        var dy by remember {mutableStateOf(0F)}
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
        LaunchedEffect(Unit) {
            while (true) {
                delay(1000/60)
                player.move(dx/100, dy/100)
            }
        }
    }
}