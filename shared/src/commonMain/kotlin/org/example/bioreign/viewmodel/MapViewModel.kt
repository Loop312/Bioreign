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

    val tileSize = 100

    init {
        loadTestMap()
    }

    fun loadMap(map: MapState) {
        _mapState.value = map
    }

    fun loadTestMap() {
        _mapState.update { currentState ->
            val tiles = Array(currentState.tiles.size) { Array(currentState.tiles[0].size) { Tile() } }

            for (i in 0 until tiles.size ) {
                for (j in 0 until tiles[i].size) {
                    when (i % 3 + j % 3) {
                        0 -> tiles[i][j] = Tile(color = Color.Green)
                        1 -> tiles[i][j] = Tile(color = Color.Red)
                        2 -> tiles[i][j] = Tile(color = Color.Blue)
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