package org.example.bioreign.viewmodel

import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.input.pointer.PointerInputChange
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import org.example.bioreign.model.OverlayState

class OverlayViewModel {
    private val _overlayState = MutableStateFlow(OverlayState())
    val overlayState: StateFlow<OverlayState> = _overlayState.asStateFlow()

    fun toggleFreeStick() {
        _overlayState.update { it.copy(freeStick = !it.freeStick) }
    }
    fun toggleOverlay() {
        _overlayState.update { it.copy(isOpen = !it.isOpen) }
    }

    val moveStick = { _: PointerInputChange, dragAmount: Offset ->
        _overlayState.update { state ->
            val newStickX = (state.stickX + dragAmount.x).coerceIn(-50F..50F)
            val newStickY = (state.stickY + dragAmount.y).coerceIn(-50F..50F)
            state.copy(
                stickX = newStickX,
                stickY = newStickY
            )
        }
    }

    fun resetStick() {
        _overlayState.update { it.copy(stickX = 0F, stickY = 0F) }
    }
}