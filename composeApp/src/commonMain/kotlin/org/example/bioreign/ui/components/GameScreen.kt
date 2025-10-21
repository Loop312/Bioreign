package org.example.bioreign.ui.components

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import io.github.compose_keyhandler.KeyHandlerHost
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

    //val focusRequester = remember { FocusRequester() }

    KeyHandlerHost(keyHandler = keyHandler, contentAlignment = Alignment.Center) {
        //background
        LoadMap(mapState.value, cameraState.value, cameraViewModel)
        //foreground
        Player(playerState.value, playerViewModel, cameraState.value, mapState.value)
    }
    //UI
    LoadOverlay(overlayState.value, overlayViewModel, playerViewModel)
    DisplayHUD(playerState.value)

    LaunchedEffect(Unit) {
        keyHandler.setupPlayer(viewModel.player)
        //focusRequester.requestFocus()
        viewModel.gameLoop()
    }
}