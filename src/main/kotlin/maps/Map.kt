package maps

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.*
import player

class Map(val theMap: String) {
    var size = 1000.dp

    @Composable
    fun load() {

        Image(
            painter = painterResource(theMap),
            contentDescription = "lol",
            contentScale = ContentScale.Fit,
            modifier = Modifier
                .size(size)
                .scale(10f)
                .offset((player.x * -1).dp, (player.y * -1).dp)

        )
    }
}