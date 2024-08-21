package maps

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.*

class Map(val theMap: String) {
    var size = 1000.dp
    var x by mutableStateOf(0)
    var y by mutableStateOf(0)
    var mapEdge by mutableStateOf(false)

    @Composable
    fun load() {
        Image(
            painter = painterResource(theMap),
            contentDescription = "lol",
            contentScale = ContentScale.Fit,
            modifier = Modifier
                .size(size)
                .scale(10f)
                .offset(x.dp, y.dp)
        )
    }
}