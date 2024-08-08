//compose imports
import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.window.*
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.*
import androidx.compose.ui.platform.*
import androidx.compose.ui.input.key.*
import androidx.compose.ui.res.*
import androidx.compose.ui.unit.dp
//project imports
import characters.*
//import demo.src.main.resources.drawable

@Composable
@Preview
fun App() {
    //movableBox()
    lol()
}

fun main() = application {
    Window(onCloseRequest = ::exitApplication) {
        App()
    }
    val player = Human()
    player.displayStats()

    println("LEVEL UP SIMULATOR TEST\n\n")
    player.lvlupSim(5)
}

@Composable
fun theButton() {
    val player = Human()
    var text by remember { mutableStateOf("LEVEL UP") }
    MaterialTheme {
        Button(onClick = {
            player.lvlupSim(5)
        }) {
            Text(text)
        }
    }
}

@Composable
fun movableBox() {
    var x by remember { mutableStateOf(0.dp) }
    var y by remember { mutableStateOf(0.dp) }
    Image(
        painter = painterResource("TestCircle.png"),
        contentDescription = "lol",
        modifier = Modifier
            .offset (x, y)
            .onKeyEvent {event: KeyEvent ->
            if (event.type == KeyEventType.KeyUp){
                x += 10.dp
            }
            if (event.type == KeyEventType.KeyDown){
                y += 10.dp
            }
            false
        }
    )
}
/*
@Composable
fun displayImage(){
    AsyncImage(
        model = "https://example.com/image.jpg",
        contentDescription = "Translated description of what the image contains"
    )
}
*/
@Composable
fun lol() {
    var player = Human()
    var text1 by remember { mutableStateOf(player.displayStats()) }
    var text2 by remember { mutableStateOf("") }
    Row {
        MaterialTheme {
            Button(onClick = {
                text1 = player.lvlupSim(1)

            }) {
                Text(text1)
            }
            //theButton()
        }
        TextField(
            value = text2,
            onValueChange = { text2 = it },
            modifier = Modifier.onKeyEvent { event: KeyEvent ->
                if (event.type == KeyEventType.KeyUp) {
                    text1 = player.lvlupSim(1)
                }
                false
            }
        )
    }
}