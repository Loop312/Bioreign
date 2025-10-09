package org.example.bioreign.model

data class OverlayState(
    val isOpen: Boolean = false,
    val freeStick: Boolean = false,
    val stickX: Float = 0F,
    val stickY: Float = 0F
)