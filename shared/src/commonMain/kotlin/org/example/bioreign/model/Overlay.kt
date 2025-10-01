package org.example.bioreign.model

data class OverlayState(
    val dx: Float = 0F,
    val dy: Float = 0F,
    val isOpen: Boolean = false,
    val joystickType: Boolean = true,
    val frameRate: Double = 60.0,
    val frameRateMultiplier: Double = 1.0
)
