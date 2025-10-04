package org.example.bioreign.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.gestures.detectDragGestures
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.hapticfeedback.HapticFeedbackType
import androidx.compose.ui.input.pointer.PointerInputChange
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalHapticFeedback
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import bioreign.composeapp.generated.resources.Res
import bioreign.composeapp.generated.resources.big_stick
import bioreign.composeapp.generated.resources.small_stick2
import org.example.bioreign.model.OverlayState
import org.example.bioreign.viewmodel.CharacterViewModel
import org.example.bioreign.viewmodel.OverlayViewModel
import org.jetbrains.compose.resources.painterResource


@Composable
fun LoadOverlay(state: OverlayState, viewModel: OverlayViewModel, player: CharacterViewModel) {
    Box (Modifier.fillMaxSize()){
        if (state.isOpen) {
            if (state.freeStick) {
                JoyStick(state, viewModel, player)
            } else {
                FreeStick(state, viewModel, player)
            }
            Box(Modifier.align(Alignment.BottomEnd).offset(-100.dp, -100.dp)) {
                Buttons(player)
            }
        }
        Column (Modifier.align(Alignment.BottomEnd)) {
            Button(onClick = { viewModel.toggleFreeStick() }) {
                Text("change stick type")
            }
            Button(onClick = { viewModel.toggleOverlay() }) {
                Text("Toggle Overlay")
            }
        }
    }
}
@Composable
fun JoyStick(state: OverlayState, viewModel: OverlayViewModel, player: CharacterViewModel) {
    val hapticFeedback = LocalHapticFeedback.current
    Box(Modifier.fillMaxSize()) {
        Box(Modifier.offset(10.dp, (-10).dp).size(100.dp).align(Alignment.BottomStart)) {
            Image(
                painterResource(Res.drawable.big_stick),
                "Stick Background",
                Modifier.alpha(.5F).size(100.dp).align(Alignment.BottomStart)
            )
            Image(
                painterResource(Res.drawable.small_stick2),
                "Stick",
                Modifier
                    .alpha(.5F)
                    .size(100.dp)
                    .align(Alignment.Center)
                    .offset(state.dx.dp, state.dy.dp)
                    .pointerInput(Unit) {
                        detectDragGestures(
                            onDragStart = { hapticFeedback.performHapticFeedback(HapticFeedbackType.LongPress)},
                            onDragEnd = {
                                hapticFeedback.performHapticFeedback(HapticFeedbackType.LongPress)
                                viewModel.updateDx(0F)
                                viewModel.updateDy(0F)
                            },
                            onDrag = moveStick(state, viewModel)
                        )
                    }
            )
            Text("dx: ${state.dx}, dy: ${state.dy}")
        }
    }
    player.move(state.dx, state.dy)
}

@Composable
fun FreeStick(state: OverlayState, viewModel: OverlayViewModel, player: CharacterViewModel) {
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
                    see = false
                    viewModel.updateDx(0F)
                    viewModel.updateDy(0F)
                    hapticFeedback.performHapticFeedback(HapticFeedbackType.LongPress)
                },
                onDrag = moveStick(state, viewModel)
            )
        }
    ) {
        Box(Modifier.offset { IntOffset(touchPosition.value.x.toInt(), touchPosition.value.y.toInt()) }) {
            if (see) {
                Image(
                    painter = painterResource(Res.drawable.big_stick),
                    contentDescription = null,
                    modifier = Modifier
                        .size(100.dp)
                        .offset((-50).dp, (-50).dp)
                        .alpha(.5F)
                )
                Image(
                    painterResource(Res.drawable.small_stick2),
                    "Stick",
                    Modifier
                        .alpha(.5F)
                        .size(100.dp)
                        .offset((state.dx - 50).dp, (state.dy - 50).dp)
                )
            }
            Text("dx: ${state.dx}, dy: ${state.dy}")
        }
    }
    player.move(state.dx, state.dy)
}
//add things to this so it can recognize direction changes, and collisions
fun moveStick(state: OverlayState, viewModel: OverlayViewModel) = { _: PointerInputChange, dragAmount: Offset ->
    var dx = state.dx
    var dy = state.dy
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
    viewModel.updateDx(dx)
    viewModel.updateDy(dy)
}

@Composable
fun Buttons(player: CharacterViewModel) {
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