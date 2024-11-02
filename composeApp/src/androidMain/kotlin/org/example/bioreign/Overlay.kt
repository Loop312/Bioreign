package org.example.bioreign

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp

class Overlay {

    @Composable
    fun open() {
        joyStick()
    }
    @Composable
    fun joyStick() {
        Box(Modifier.fillMaxSize()) {
            Box(Modifier.offset(10.dp, (-10).dp).size(100.dp).align(Alignment.BottomStart)) {
                Image(
                    painterResource(R.drawable.big_stick),
                    "lol",
                    Modifier.alpha(.5F).size(100.dp)
                )
                Image(
                    painterResource(R.drawable.small_stick),
                    "lol",
                    Modifier.alpha(.5F).size(50.dp).align(Alignment.Center)
                )
            }
        }
    }
}