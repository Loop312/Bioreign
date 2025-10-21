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

    fun updatePosition(playerX: Float, playerY: Float, mapSizeX: Int, mapSizeY: Int, tileSize: Float) {
        _cameraState.update { cameraState ->

            val clampTop = playerY < 0
            val clampBottom = playerY > mapSizeY * tileSize - cameraState.height * tileSize
            val clampLeft = playerX < 0
            val clampRight = playerX > mapSizeX * tileSize - cameraState.width * tileSize

            val startX = if (clampRight) mapSizeX - cameraState.width
                else (playerX / tileSize).toInt().coerceAtLeast(0)
            val startY = if (clampBottom) mapSizeY - cameraState.height
                else (playerY / tileSize).toInt().coerceAtLeast(0)

            val endX = if (clampLeft) cameraState.width
                else (playerX / tileSize + cameraState.width).toInt().coerceAtMost(mapSizeX)
            val endY = if (clampTop) cameraState.height
                else (playerY / tileSize + cameraState.height).toInt().coerceAtMost(mapSizeY)

            val offsetX = if (clampLeft) 0f
                else if (clampRight) ((mapSizeX * tileSize) % tileSize)
                else playerX % tileSize
            val offsetY = if (clampTop) 0f
                else if (clampBottom) ((mapSizeY * tileSize) % tileSize)
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

    fun updateCameraSize(canvasWidth: Float, canvasHeight: Float, tileSize: Float) {
        _cameraState.update { cameraState ->
            val width = (canvasWidth / tileSize).toInt()
            val height = (canvasHeight / tileSize).toInt()
            //offsets the map to make movement smooth
            val x = -(cameraState.startX * tileSize + cameraState.offset.x)
            val y = -(cameraState.startY * tileSize + cameraState.offset.y)
            //centers the camera
            val centerOffsetX = (cameraState.canvasWidth - cameraState.width * tileSize) / 2
            val centerOffsetY = (cameraState.canvasHeight - cameraState.height * tileSize) / 2

            val translateX = centerOffsetX + x
            val translateY = centerOffsetY + y
            cameraState.copy(
                width = width,
                height = height,
                canvasWidth = canvasWidth,
                canvasHeight = canvasHeight,
                translateX = translateX,
                translateY = translateY
            )
        }
    }
}