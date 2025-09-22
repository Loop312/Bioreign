package org.example.bioreign.model

import androidx.compose.ui.geometry.Offset

data class CameraState (
    val startX: Int = 0,
    val startY: Int = 0,
    val endX: Int = 5,
    val endY: Int = 5,
    val offset: Offset = Offset(0f, 0f),
    val width: Int = 11,
    val height: Int = 11,
    val clampTop: Boolean = false,
    val clampBottom: Boolean = false,
    val clampLeft: Boolean = false,
    val clampRight: Boolean = false
)