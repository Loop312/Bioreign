package menus

import androidx.compose.foundation.layout.Box
import androidx.compose.material.Text
import androidx.compose.runtime.*

open class Menu {
    open var isOpen by mutableStateOf(false)

    @Composable
    open fun open(){
        Box {
            Text("MENU IS OPEN")
        }
    }
}