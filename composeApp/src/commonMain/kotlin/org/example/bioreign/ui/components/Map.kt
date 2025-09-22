package org.example.bioreign.ui.components

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.offset
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.geometry.center
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.unit.dp
import org.example.bioreign.model.CameraState
import org.example.bioreign.model.MapState
import org.example.bioreign.viewmodel.CameraViewModel

@Composable
fun LoadMap(map: MapState, cameraState: CameraState, cameraVM: CameraViewModel) {
    Canvas(Modifier.fillMaxSize().offset(
        x = -(cameraState.startX * map.tileSize + cameraState.offset.x).dp,
        y = -(cameraState.startY * map.tileSize + cameraState.offset.y).dp
    )) {
        val canvasCenter = size.center //probably be used to center the camera
        val canvasWidth = size.width
        val canvasHeight = size.height
        //println("Center: $center, Width: $canvasWidth, Height: $canvasHeight")

        val viewableTilesX = (canvasWidth / map.tileSize).toInt()
        val viewableTilesY = (canvasHeight / map.tileSize).toInt()
        //println("Viewable Tiles: $viewableTilesX x $viewableTilesY")
        cameraVM.updateCameraSize(viewableTilesX, viewableTilesY)

        println("Camera State: $cameraState")

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