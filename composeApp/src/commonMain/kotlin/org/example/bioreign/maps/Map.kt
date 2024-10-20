package maps

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.*
import org.example.bioreign.player
import org.jetbrains.compose.resources.*

class Map(val theMap: DrawableResource) {
    var size = 1000.dp
    var x by mutableStateOf(0)
    var y by mutableStateOf(0)
    var mapEdge by mutableStateOf(false)

    var canMoveLeft = true
    var canMoveRight = true
    var canMoveUp = true
    var canMoveDown = true
    //after figuring out how they'll be implemented, change data type
    var upColliders = mutableListOf<Array<Int>>()
    var downColliders = mutableListOf<Array<Int>>()
    var leftColliders = mutableListOf<Array<Int>>()
    var rightColliders = mutableListOf<Array<Int>>()

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
    //coordinates must be in the following format
    //[0] = axis
    //[1 and 2] = locations on that axis
    fun addCollider(type: String, coordinates: Array<Int>) {
        if (coordinates.size == 3) {
            when (type) {
                "up" -> upColliders.add(coordinates)
                "down" -> downColliders.add(coordinates)
                "left" -> leftColliders.add(coordinates)
                "right" -> rightColliders.add(coordinates)
                else -> println("Collider type spelt incorrectly")
            }
        }
        else println("invalid array size for collider")
    }

    //make edits later
    //[0] = axis
    //[1 and 2] = locations on that axis
    //make sure > and < are right
    fun checkCollisions() {
        canMoveLeft = true
        canMoveRight = true
        canMoveUp = true
        canMoveDown = true
        if (player.movingUp) {
            for (colliders in upColliders) {
                if(y == colliders[0] && x > colliders[1] && x < colliders[2]){
                    canMoveUp = false
                }
            }
        }
        else if (player.movingDown) {
            for (colliders in downColliders) {
                if(y == colliders[0] && x > colliders[1] && x < colliders[2]){
                    canMoveDown = false
                }
            }
        }
        else if (player.movingLeft) {
            for (colliders in leftColliders) {
                if(x == colliders[0] && y < colliders[1] && y > colliders[2]){
                    canMoveLeft = false
                }
            }
        }
        else if (player.movingRight) {
            for (colliders in rightColliders) {
                if(x == colliders[0] && y < colliders[1] && y > colliders[2]){
                    canMoveRight = false
                }
            }
        }
    }

    fun displayColliders(type: String): String{
        var returnedValue = "["
        val colliders = if (type == "up") upColliders
                        else if (type == "down") downColliders
                        else if (type == "left") leftColliders
                        else if (type == "right") rightColliders
                        else emptyList<Array<Int>>()

        for (i in colliders.indices) {
            for (j in 0..2){
                returnedValue += colliders[i][j]
                returnedValue += ","
            }
            returnedValue += "]"
            if (i != colliders.size - 1) returnedValue += "["
        }
        return returnedValue
    }
}