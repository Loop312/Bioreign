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
    var collisions = mutableListOf< Pair< Pair<Dp,Dp> , Pair<Dp,Dp> >>()

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
    fun addCollider(xy1: Pair<Dp, Dp>, xy2: Pair<Dp,Dp>) {
        collisions.add(Pair(xy1,xy2))
    }

    //make edits later
    fun checkCollisions() {
        //need to figure out how this'll work
        //the 1st "first" means the 1st location given
        //the 2nd "first" means the x of the first location given
        //maybe try something else that's easier to understand...
        if (player.x.dp > collisions[1].first.first){
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