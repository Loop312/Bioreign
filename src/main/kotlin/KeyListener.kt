import androidx.compose.ui.input.key.*

class KeyListener () {

    var keys = arrayOf(
        Key.A, Key.DirectionLeft,  //Left: 0,1
        Key.D, Key.DirectionRight, //Right: 2,3
        Key.W, Key.DirectionUp,    //Up: 4,5
        Key.S, Key.DirectionDown,   //Down: 6,7
        Key.ShiftLeft               //Sprint: 8
    )
    var esc = true

    fun listen(pressedKeys: Set<Key>) {
        //MOVEMENT
        if (keys[0] in pressedKeys || keys[1] in pressedKeys) {
            if (map.canMoveLeft) {
                player.move(-1, 0)
            }
        }
        if (keys[2] in pressedKeys || keys[3] in pressedKeys) {
            if (map.canMoveRight) {
                player.move(1, 0)
            }
        }
        if (keys[4] in pressedKeys || keys[5] in pressedKeys) {
            if (map.canMoveUp) {
                player.move(0, -1)
            }
        }
        if (keys[6] in pressedKeys || keys[7] in pressedKeys) {
            if (map.canMoveDown) {
                player.move(0, 1)
            }
        }
        if (Key.ShiftLeft in pressedKeys && player.stamina > 0) {
            player.sprinting = true
            player.stamina -= 0.1
        } else {
            player.sprinting = false
        }

        //ATTACK AND SPELLS


        //access menu
        if (Key.Escape in pressedKeys && esc) {
            gameMenu.isOpen = !gameMenu.isOpen
            println("Toggle menu")
            esc = false
        }
    }

    //create a function for handling keybind changes
}