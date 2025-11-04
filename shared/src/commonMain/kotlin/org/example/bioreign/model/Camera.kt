package org.example.bioreign.model

import androidx.compose.ui.geometry.Offset

data class CameraState (
    val startX: Int = 0,
    val startY: Int = 0,
    val endX: Int = 5,
    val endY: Int = 5,
    val offset: Offset = Offset(0f, 0f),
    val translateX: Float = 0f,
    val translateY: Float = 0f,
    val width: Int = 0,
    val height: Int = 0,
    val canvasWidth: Float = 0f,
    val canvasHeight: Float = 0f
)