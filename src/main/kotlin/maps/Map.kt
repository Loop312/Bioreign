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
    //after figuring out how they'll be implemented, change data type
    var collisions = mutableListOf<Array<Int>>()

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
    fun addCollider(coordinates: Array<Int>) {
        collisions.add(coordinates)
    }

    //make edits later
    fun checkCollisions() {
        //iterates over the list of collisions going through coordinates
        //[0] = 1st x
        //[1] = 2nd y
        //[2] = 1st x
        //[3] = 2nd y
        canMoveLeft = true
        canMoveRight = true
        canMoveUp = true
        canMoveDown = true
        for (collider in collisions) {
            //makes things easier to discern
            val (colliderLeft, colliderTop, colliderRight, colliderBottom) = collider

            // check x then y
            if (x > colliderLeft && x < colliderRight) {
                if (y < colliderTop) { canMoveDown = false }
                else if (y > colliderBottom) { canMoveUp = false }
            }
            // check y then x
            if (y > colliderTop && y < colliderBottom) {
                if (x < colliderLeft) { canMoveRight = false }
                else if (x > colliderRight) { canMoveDown = false }
            }
        }
    }
}