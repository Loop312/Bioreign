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

            val clampTop = playerY < 0
            val clampBottom = playerY > mapSizeY * tileSize
            val clampLeft = playerX < 0
            val clampRight = playerX > mapSizeX * tileSize

            val startX = if (clampRight) mapSizeX - cameraState.width
                else (playerX / tileSize).toInt().coerceAtLeast(0)
            val startY = if (clampBottom) mapSizeY - cameraState.height
                else (playerY / tileSize).toInt().coerceAtLeast(0)

            val endX = if (clampLeft) cameraState.width
                else (playerX / tileSize + cameraState.width).toInt().coerceAtMost(mapSizeX)
            val endY = if (clampTop) cameraState.height
                else (playerY / tileSize + cameraState.height).toInt().coerceAtMost(mapSizeY)

            val offsetX = if (clampLeft) 0f
                else if (clampRight) (mapSizeX * tileSize - cameraState.width).toFloat()
                else playerX % tileSize
            val offsetY = if (clampTop) 0f
                else if (clampBottom) (mapSizeY * tileSize - cameraState.height).toFloat()
                else playerY % tileSize

            val offset = Offset(
                offsetX,
                offsetY
            )
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