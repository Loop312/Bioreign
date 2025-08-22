package org.example.bioreign.viewmodel

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import org.example.bioreign.model.CameraState
import org.example.bioreign.model.MapState
import org.example.bioreign.model.Tile
import org.example.bioreign.model.TileType

class MapViewModel : ViewModel() {
    private val _mapState = MutableStateFlow(MapState())
    val mapState: StateFlow<MapState> = _mapState.asStateFlow()

    private val _cameraState = MutableStateFlow(CameraState())
    val cameraState: StateFlow<CameraState> = _cameraState.asStateFlow()


    fun loadMap(map: MapState) {
        _mapState.value = map
    }

    fun updateCameraPosition(x: Float, y: Float) {
        _cameraState.value = _cameraState.value.copy(x = x, y = y)
    }

    fun updateCameraSize(width: Int, height: Int) {
        _cameraState.value = _cameraState.value.copy(width = width, height = height)
    }

    fun updateCameraClamp(clamp: Boolean) {
        _cameraState.value = _cameraState.value.copy(clamp = clamp)
    }

    fun TileToImage(tile: Tile): String {
        return when (tile.type) {
            TileType.GRASS -> "grass"
            TileType.DIRT -> "dirt"
            TileType.STONE -> "stone"
            TileType.WATER -> "water"
        }
    }
}