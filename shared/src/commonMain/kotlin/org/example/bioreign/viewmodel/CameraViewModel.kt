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

    fun updatePosition(
        playerX: Float, playerY: Float,
        mapSizeX: Int, mapSizeY: Int,
        tileSize: Float
    ) {
        _cameraState.update { cameraState ->
            val mapHeight = mapSizeY * tileSize
            val mapWidth = mapSizeX * tileSize

            val viewportHeight = cameraState.canvasHeight
            val viewportWidth = cameraState.canvasWidth

            val camMinX = viewportWidth / 2
            val camMinY = viewportHeight / 2

            val camMaxX = mapWidth - camMinX
            val camMaxY = mapHeight - camMinY

            val targetX = playerX
            val targetY = playerY

            val clampedX = targetX.coerceIn(camMinX, camMaxX)
            val clampedY = targetY.coerceIn(camMinY, camMaxY)

            val offset = Offset(
                viewportWidth / 2 - clampedX,
                viewportHeight / 2 - clampedY
            )

            val cameraTopLeftWorldX = clampedX - viewportWidth / 2f
            val cameraTopLeftWorldY = clampedY - viewportHeight / 2f

            // Start tile indices for rendering (Use .coerceAtLeast(0) for safety)
            val startX = (cameraTopLeftWorldX / tileSize).toInt().coerceAtLeast(0)
            val startY = (cameraTopLeftWorldY / tileSize).toInt().coerceAtLeast(0)

            // Draw up to an extra two tiles for smoother scrolling
            val endX = (startX + cameraState.width + 2).coerceAtMost(mapSizeX)
            val endY = (startY + cameraState.height + 2).coerceAtMost(mapSizeY)

            cameraState.copy(
                startX = startX,
                startY = startY,
                endX = endX,
                endY = endY,
                translateX = clampedX,
                translateY = clampedY,
                offset = offset
            )
        }
    }

    fun updateCameraSize(canvasWidth: Float, canvasHeight: Float, tileSize: Float) {
        _cameraState.update { cameraState ->
            val width = (canvasWidth / tileSize).toInt()
            val height = (canvasHeight / tileSize).toInt()
            cameraState.copy(
                width = width,
                height = height,
                canvasWidth = canvasWidth,
                canvasHeight = canvasHeight
            )
        }
    }
}