package org.example.bioreign.model

import androidx.compose.ui.graphics.Color

data class MapState (
    val tileSize: Float = 0f,
    val tiles: Array<Array<Tile>> = arrayOf(arrayOf(Tile()))
)

data class Tile (
    val type: TileType = TileType.GRASS,
    val solid: Boolean = false,
    val color: Color = Color.Green
)

enum class TileType {
    GRASS, DIRT, STONE, WATER
}