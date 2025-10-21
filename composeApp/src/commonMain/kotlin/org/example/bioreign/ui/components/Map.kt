package org.example.bioreign.ui.components

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.graphics.drawscope.translate
import androidx.compose.ui.platform.LocalDensity
import org.example.bioreign.model.CameraState
import org.example.bioreign.model.MapState
import org.example.bioreign.viewmodel.CameraViewModel
import org.example.bioreign.viewmodel.MapViewModel

@Composable
fun LoadMap(map: MapState, mapVM: MapViewModel, cameraState: CameraState, cameraVM: CameraViewModel) {
    val density = LocalDensity.current
    Canvas(Modifier.fillMaxSize()) {
        val newTileSize = with(density) { mapVM.BASE_TILE_SIZE_DP.toPx() }
        if (map.tileSize != newTileSize) {
            mapVM.setTileSize(newTileSize)
        }
        cameraVM.updateCameraSize(size.width, size.height, map.tileSize)
        translate (cameraState.translateX, cameraState.translateY) {
            for (i in cameraState.startX until cameraState.endX) {
                for (j in cameraState.startY until cameraState.endY) {
                    val offset = Offset(i * map.tileSize, j * map.tileSize)
                    drawRect(
                        color = map.tiles[i][j].color,
                        topLeft = offset,
                        size = Size(map.tileSize, map.tileSize)
                    )
                    drawGrid(
                        offset,
                        Size(map.tileSize, map.tileSize)
                    )
                }
            }
        }
    }
}

fun DrawScope.drawGrid(offset: Offset, size: Size) {
    drawLine(
        color = Color.Black,
        start = Offset(offset.x, offset.y),
        end = Offset(offset.x + size.width, offset.y)
    )
    drawLine(
        color = Color.Black,
        start = Offset(offset.x, offset.y),
        end = Offset(offset.x, offset.y + size.height)
    )
}