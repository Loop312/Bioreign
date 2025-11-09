package org.example.bioreign.viewmodel

//import androidx.lifecycle.ViewModel
import androidx.compose.ui.graphics.Color
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import org.example.bioreign.model.MapState
import org.example.bioreign.model.Tile
import org.example.bioreign.model.TileType

class MapViewModel {
    private val _mapState = MutableStateFlow(MapState())
    val mapState: StateFlow<MapState> = _mapState.asStateFlow()

    init {
        loadTestMap(30,30)
    }

    fun setTileSize(pixelSize: Float) {
        _mapState.update { currentState ->
            currentState.copy(tileSize = pixelSize)
        }
    }

    fun loadMap(map: MapState) {
        _mapState.value = map
    }

    fun loadTestMap(sizeX: Int, sizeY: Int) {
        _mapState.update { currentState ->
            val tiles = Array(sizeX) { Array(sizeY) { Tile() } }

            for (i in 0 until sizeX ) {
                for (j in 0 until sizeY) {
                    when (i % 5 + j % 5) {
                        0 -> tiles[i][j] = Tile(color = Color.Green)
                        1 -> tiles[i][j] = Tile(color = Color.Red, solid = true)
                        2 -> tiles[i][j] = Tile(color = Color.Blue)
                        3 -> tiles[i][j] = Tile(color = Color.Yellow)
                        4 -> tiles[i][j] = Tile(color = Color.Magenta)
                    }
                }
            }
            currentState.copy(tiles = tiles)
        }
    }

    fun tileToImage(tile: Tile): String {
        return when (tile.type) {
            TileType.GRASS -> "grass"
            TileType.DIRT -> "dirt"
            TileType.STONE -> "stone"
            TileType.WATER -> "water"
        }
    }

    fun tileToSolid(tile: Tile): Boolean {
        return when (tile.type) {
            TileType.GRASS -> false
            TileType.DIRT -> false
            TileType.STONE -> true
            TileType.WATER -> true
        }
    }
}