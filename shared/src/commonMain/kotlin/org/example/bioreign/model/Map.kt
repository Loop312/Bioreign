package org.example.bioreign.model

data class MapState (
    val width: Int = 0,
    val height: Int = 0,
    val tiles: Array<Array<Tile>> = emptyArray()
)

data class Tile (
    val x: Int,
    val y: Int,
    val type: TileType,
    val solid: Boolean
)

enum class TileType {
    GRASS, DIRT, STONE, WATER
}

data class CameraState (
    val x: Float = 0f,
    val y: Float = 0f,
    val width: Int = 0,
    val height: Int = 0,
    val clamp: Boolean = false
)