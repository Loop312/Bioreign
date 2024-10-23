package org.example.bioreign

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.manalkaff.jetstick.JoyStick

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            //App()
            theApp()
            JoyStick(
                Modifier.padding(30.dp),
                size = 150.dp,
                dotSize = 30.dp
            ){ x: Float, y: Float ->
                player.move(x,y)
            }
        }
    }
}

@Preview
@Composable
fun AppAndroidPreview() {
    //App()
    theApp()
}
