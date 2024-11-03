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
                    Modifier.alpha(.5F).size(100.dp)
                )
                Image(
                    painterResource(R.drawable.small_stick),
                    "Stick",
                    Modifier
                        .alpha(.5F)
                        .size(50.dp)
                        .fillMaxSize()
                        .align(Alignment.Center)
                        //.draggable(state = DraggableState{x -> dx = x}, orientation = Orientation.Horizontal)
                        //.draggable(state = DraggableState{y -> dy = y}, orientation = Orientation.Vertical)
                        .offset(dx.dp, dy.dp)
                        /*
                        .pointerInput(Unit) {
                            detectDragGestures { change, dragAmount ->
                                dx = dragAmount.x
                                dy = dragAmount.y
                            }
                        }
                        */
                        .draggable2D(state = Draggable2DState { onDelta -> dx = onDelta.x; dy = onDelta.y })
                )
                Text("dx: $dx, dy: $dy")
            }
        }
    }
}