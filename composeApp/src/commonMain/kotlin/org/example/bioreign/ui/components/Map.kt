package org.example.bioreign.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import org.example.bioreign.model.CameraState
import org.example.bioreign.model.MapState

@Composable
fun LoadMap(map: MapState, cameraState: CameraState) {

    Box(modifier = Modifier
        //.width(cameraState.width.dp)
        //.height(cameraState.height.dp)
        .fillMaxSize()
        .offset(
            if (!cameraState.clamp) -cameraState.x.dp else 0.dp,
            if (!cameraState.clamp) -cameraState.y.dp else 0.dp
        )
    ) {
        Row {
            for (rows in map.tiles) {
                Column {
                    for (columns in rows) {
                        Box(modifier = Modifier.size(100.dp).background(color = Color.Red)) {}
                        Spacer(modifier = Modifier.size(1.dp))
                    }
                }
                Spacer(modifier = Modifier.size(1.dp))
            }
        }
    }
}