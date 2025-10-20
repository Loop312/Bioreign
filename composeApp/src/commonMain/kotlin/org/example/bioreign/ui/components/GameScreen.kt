package org.example.bioreign.ui.components

import androidx.compose.foundation.focusable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.input.key.onKeyEvent
import org.example.bioreign.viewmodel.GameViewModel
import org.example.bioreign.viewmodel.keyHandler
import org.example.bioreign.viewmodel.setupPlayer

@Composable
fun GameScreen(viewModel: GameViewModel) {
    val gameState = viewModel.gameState.collectAsState()
    val playerState = viewModel.player.characterState.collectAsState()
    val mapState = viewModel.map.mapState.collectAsState()
    val cameraState = viewModel.camera.cameraState.collectAsState()
    val overlayState = viewModel.overlay.overlayState.collectAsState()
    val cameraViewModel = viewModel.camera
    val playerViewModel = viewModel.player
    val overlayViewModel = viewModel.overlay


    val focusRequester = remember { FocusRequester() }

    Box(contentAlignment = Alignment.Center, modifier = Modifier
        .fillMaxSize()
        .focusRequester(focusRequester)
        .focusable()
        .onKeyEvent(keyHandler.listen)
    ) {
        //background
        LoadMap(mapState.value, cameraState.value, cameraViewModel)
        //foreground
        Player(playerState.value, playerViewModel, cameraState.value, mapState.value)
    }
    //UI
    LoadOverlay(overlayState.value, overlayViewModel, playerViewModel, focusRequester)
    DisplayHUD(playerState.value)

    LaunchedEffect(Unit) {
        keyHandler.setupPlayer(viewModel.player)
        focusRequester.requestFocus()
        viewModel.gameLoop()
    }
}