package org.example.bioreign.model

data class MapState (
    val width: Int = 100,
    val height: Int = 100,
    val tiles: Array<Array<Tile>> = arrayOf(
        arrayOf(Tile(), Tile(), Tile(), Tile(), Tile()),
        arrayOf(Tile(), Tile(), Tile(), Tile(), Tile()),
        arrayOf(Tile(), Tile(), Tile(), Tile(), Tile()),
        arrayOf(Tile(), Tile(), Tile(), Tile(), Tile()),
        arrayOf(Tile(), Tile(), Tile(), Tile(), Tile())
    )
)

data class Tile (
    val x: Int = 0,
    val y: Int = 0,
    val type: TileType = TileType.GRASS,
    val solid: Boolean = false
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