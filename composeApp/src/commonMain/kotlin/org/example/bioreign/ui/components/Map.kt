package org.example.bioreign.ui.components

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.DrawScope
import org.example.bioreign.model.CameraState
import org.example.bioreign.model.MapState

@Composable
fun LoadMap(map: MapState, cameraState: CameraState) {
    Canvas(Modifier.fillMaxSize()) {
        val size = Size(100f, 100f)
        var offset = Offset(cameraState.x, cameraState.y)
        for (i in 0 until map.tiles.size) {
            for (j in 0 until map.tiles[i].size) {
                drawRect(
                    color = Color.Red,
                    topLeft = offset,
                    size = size
                )
                drawGrid(offset, size)
                offset = Offset(offset.x + size.width, offset.y)
            }
            drawLine(
                color = Color.Black,
                start = Offset(offset.x, offset.y),
                end = Offset(offset.x, offset.y + size.height)
            )
            offset = Offset(cameraState.x, offset.y + size.height)
        }
        for (i in 0 until map.tiles.size) {
            drawLine(
                color = Color.Black,
                start = Offset(offset.x, offset.y),
                end = Offset(offset.x + size.width, offset.y)
            )
            offset = Offset(offset.x + size.width, offset.y)
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