package org.example.bioreign.model

data class OverlayState(
    val dx: Float = 0F,
    val dy: Float = 0F,
    val isOpen: Boolean = false,
    val freeStick: Boolean = false
)