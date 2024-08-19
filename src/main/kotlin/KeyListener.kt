import androidx.compose.ui.input.key.*
import characters.*

class KeyListener () {

    var keys = arrayOf(
        Key.A, Key.DirectionLeft,  //Left: 0,1
        Key.D, Key.DirectionRight, //Right: 2,3
        Key.W, Key.DirectionUp,    //Up: 4,5
        Key.S, Key.DirectionDown   //Down: 6,7
    )

    fun listen(pressedKeys: Set<Key>, player: Character) {
        if (keys[0] in pressedKeys || keys[1] in pressedKeys) {
            player.move(-5, 0)
        }
        if (keys[2] in pressedKeys || keys[3] in pressedKeys) {
            player.move(5, 0)
        }
        if (keys[4] in pressedKeys || keys[5] in pressedKeys) {
            player.move(0, -5)
        }
        if (keys[6] in pressedKeys || keys[7] in pressedKeys) {
            player.move(0, 5)
        }
    }
}