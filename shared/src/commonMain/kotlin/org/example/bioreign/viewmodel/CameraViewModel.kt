package org.example.bioreign.viewmodel

import androidx.compose.ui.geometry.Offset
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import org.example.bioreign.model.CameraState

class CameraViewModel {
    private val _cameraState = MutableStateFlow(CameraState())
    val cameraState: StateFlow<CameraState> = _cameraState.asStateFlow()

    fun updatePosition(playerX: Float, playerY: Float, mapSizeX: Int, mapSizeY: Int, tileSize: Int) {
        _cameraState.update { cameraState ->
            var startX = (playerX / tileSize).toInt().coerceAtLeast(0)
            var startY = (playerY / tileSize).toInt().coerceAtLeast(0)

            var endX = (playerX / tileSize + cameraState.width).toInt().coerceAtMost(mapSizeX)
            var endY = (playerY / tileSize + cameraState.height).toInt().coerceAtMost(mapSizeY)

            val clampTop = startY == 0
            val clampBottom = endY == mapSizeY
            val clampLeft = startX == 0
            val clampRight = endX == mapSizeX

            endX = if (clampLeft) cameraState.width else endX
            endY = if (clampTop) cameraState.height else endY

            startX = if (clampRight) mapSizeX - cameraState.width else startX
            startY = if (clampBottom) mapSizeY - cameraState.height else startY

            val offset = Offset(playerX % tileSize, playerY % tileSize)
            cameraState.copy(
                startX = startX,
                startY = startY,
                endX = endX,
                endY = endY,
                offset = offset,
                clampTop = clampTop,
                clampBottom = clampBottom,
                clampLeft = clampLeft,
                clampRight = clampRight
            )
        }
    }

    fun updateCameraSize(width: Int, height: Int) {
        _cameraState.update {
            it.copy(width = width, height = height)
        }
    }
}