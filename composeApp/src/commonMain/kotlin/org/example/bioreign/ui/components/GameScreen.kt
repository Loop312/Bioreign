package org.example.bioreign.ui.components

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import org.example.bioreign.DisplayHUD
import org.example.bioreign.viewmodel.GameViewModel

@Composable
fun GameScreen(viewModel: GameViewModel) {
    val gameState = viewModel.gameState.collectAsState()
    val playerState = viewModel.player.characterState.collectAsState()


    LaunchedEffect(Unit) {
        viewModel.gameLoop()
    }
    DisplayHUD(playerState.value.stats)
}