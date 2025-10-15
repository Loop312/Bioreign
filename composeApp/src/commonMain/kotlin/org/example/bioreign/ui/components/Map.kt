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
import org.example.bioreign.model.CameraState
import org.example.bioreign.model.MapState
import org.example.bioreign.viewmodel.CameraViewModel

@Composable
fun LoadMap(map: MapState, cameraState: CameraState, cameraVM: CameraViewModel) {
    Canvas(Modifier.fillMaxSize()) {
        val canvasWidth = size.width
        val canvasHeight = size.height
        //println("Center: $center, Width: $canvasWidth, Height: $canvasHeight")

        val viewableTilesX = (canvasWidth / map.tileSize).toInt()
        val viewableTilesY = (canvasHeight / map.tileSize).toInt()
        //println("Viewable Tiles: $viewableTilesX x $viewableTilesY")
        cameraVM.updateCameraSize(viewableTilesX, viewableTilesY)

        //println("Camera State: $cameraState")

        //offsets the map to make movement smooth
        val x = -(cameraState.startX * map.tileSize + cameraState.offset.x)
        val y = -(cameraState.startY * map.tileSize + cameraState.offset.y)

        //centers the camera
        val centerOffsetX = (canvasWidth - viewableTilesX * map.tileSize) / 2
        val centerOffsetY = (canvasHeight - viewableTilesY * map.tileSize) / 2

        translate (centerOffsetX + x, centerOffsetY + y) {
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