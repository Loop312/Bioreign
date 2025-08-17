package org.example.bioreign.ui.components

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import org.example.bioreign.hud
import org.example.bioreign.viewmodel.GameViewModel

@Composable
fun GameScreen(viewModel: GameViewModel) {
    val state = viewModel.gameState.collectAsState()
    LaunchedEffect(Unit) {
        viewModel.gameLoop()
    }
    hud.display()
}