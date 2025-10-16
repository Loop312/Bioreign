package org.example.bioreign.model

import androidx.compose.ui.graphics.Color

data class MapState (
    val tileSize: Float = 100f,
    val tiles: Array<Array<Tile>> = arrayOf(arrayOf(Tile()))
)

data class Tile (
    val x: Int = 0,
    val y: Int = 0,
    val type: TileType = TileType.GRASS,
    val solid: Boolean = false,
    val color: Color = Color.Green
)

enum class TileType {
    GRASS, DIRT, STONE, WATER
}