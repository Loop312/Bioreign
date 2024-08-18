package characters

import androidx.compose.ui.input.key.*

class Player() : Character() {
    var keys = arrayOf( Key.A, Key.DirectionLeft,  //Left: 0,1
                        Key.D, Key.DirectionRight, //Right: 2,3
                        Key.W, Key.DirectionUp,    //Up: 4,5
                        Key.S, Key.DirectionDown   //Down: 6,7
    )

    fun move(pressedKeys: Set<Key>) {
        if (keys[0] in pressedKeys || keys[1] in pressedKeys) x -= 5
        if (keys[2] in pressedKeys || keys[3] in pressedKeys) x += 5
        if (keys[4] in pressedKeys || keys[5] in pressedKeys) y -= 5
        if (keys[6] in pressedKeys || keys[7] in pressedKeys) y += 5
    }
}