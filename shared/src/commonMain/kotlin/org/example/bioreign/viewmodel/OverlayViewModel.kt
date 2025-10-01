package org.example.bioreign.viewmodel

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import org.example.bioreign.model.OverlayState

class OverlayViewModel {
    private val _overlayState = MutableStateFlow(OverlayState())
    val overlayState: StateFlow<OverlayState> = _overlayState.asStateFlow()

    fun swapJoystickType() {
        _overlayState.update { it.copy(joystickType = !it.joystickType) }
    }
    fun toggleOverlay() {
        _overlayState.update { it.copy(isOpen = !it.isOpen) }
    }
    fun updateDx(dx: Float) {
        _overlayState.update { it.copy(dx = dx) }
    }
    fun updateDy(dy: Float) {
        _overlayState.update { it.copy(dy = dy) }
    }

}