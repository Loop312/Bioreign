package org.example.bioreign.viewmodel

import androidx.compose.runtime.withFrameNanos
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import org.example.bioreign.model.GameState

class GameViewModel(val player: CharacterViewModel) : ViewModel() {
    var updateInterval = .5f

    private val _gameState = MutableStateFlow(GameState())
    val gameState: StateFlow<GameState> = _gameState.asStateFlow()

    suspend fun gameLoop() {
        var lastFrameTimeNanos = 0L
        var fpsUpdateTimer = 0f
        while (true) {
            withFrameNanos { frameTimeNanos: Long ->
                val deltaTime = if (lastFrameTimeNanos == 0L) {
                    0f //can't do 0/1_000_000_000f
                } else {
                    (frameTimeNanos - lastFrameTimeNanos) / 1_000_000_000f
                }
                fpsUpdateTimer += deltaTime
                if (fpsUpdateTimer >= updateInterval) {
                    val currentFrameTime = deltaTime * 1000
                    val currentFps = 1 / deltaTime
                    _gameState.update {
                        it.copy(
                            frameTime = currentFrameTime,
                            fps = currentFps
                        )
                    }
                    fpsUpdateTimer = 0f
                }
                lastFrameTimeNanos = frameTimeNanos
                update(deltaTime)
            }
        }
    }
    fun update(deltaTime: Float) {
        player.gainExp(1f * deltaTime)
        player.handleStaminaRegen(deltaTime)
        player.handleMovement(deltaTime)
        player.handleManaRegen(deltaTime)
    }
}