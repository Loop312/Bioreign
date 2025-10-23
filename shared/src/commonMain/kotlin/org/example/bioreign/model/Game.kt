package org.example.bioreign.model

data class GameState(
    var isPlaying: Boolean = false,
    var fps: Float = 0f,
    var frameTime: Float = 0f,
    var showHitbox: Boolean = true,
    var map: MapState = MapState(),
    val width: Int = 0,
    val height: Int = 0,
    val minSize: Int = 0,
    val scaleFactor: Int = 8
)
