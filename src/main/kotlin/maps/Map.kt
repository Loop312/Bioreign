package maps

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.*
import player

class Map(val theMap: String) {
    var size = 1000.dp
    var x by mutableStateOf(0)
    var y by mutableStateOf(0)
    var mapEdge by mutableStateOf(false)

    var canMoveLeft = true
    var canMoveRight = true
    var canMoveUp = true
    var canMoveDown = true
    var collisions = emptySet<Int>() //after figuring out how they'll be implemented, change data type

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

    //make edits later
    fun addCollider(): Int {
        return 1
    }

    //make edits later
    fun checkCollisions() {
        if (player.x in collisions){
            canMoveLeft = false
        }
        else{
            canMoveUp = true
            canMoveDown = true
            canMoveLeft = true
            canMoveRight = true
        }
    }
}