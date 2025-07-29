package org.example.bioreign.menus

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import bioreign.composeapp.generated.resources.A
import bioreign.composeapp.generated.resources.BioreignTempLogo
import bioreign.composeapp.generated.resources.Res
import bioreign.composeapp.generated.resources.compose_multiplatform
import bioreign.composeapp.generated.resources.tempmap
import org.example.bioreign.gameLoop
import org.example.bioreign.player
import org.jetbrains.compose.resources.painterResource

class CharacterMenu {
    private val characterImages = listOf(
        Res.drawable.compose_multiplatform,
        Res.drawable.BioreignTempLogo,
        Res.drawable.tempmap,
        Res.drawable.A
    )
    @Composable
    fun open(navStoryMode: () -> Unit, navBack: () -> Unit) {
        var i by remember {  mutableStateOf(0) }
        Box(Modifier.fillMaxSize()) {
            Image(painterResource(characterImages[i]), null, Modifier
                .align(Alignment.Center)
                .size(200.dp)
//                                                                  //add check for mode
                .clickable{ player.image = characterImages[i]; navStoryMode(); gameLoop.isPlaying = true }
            )
            //% characterImages.size makes it loop through and come back to the beginning
            Button({ i = (i + 1) % characterImages.size }, Modifier.align(Alignment.CenterEnd)) {
                Text(">")
            }
            Button({ i = (i - 1 + characterImages.size) % characterImages.size }, Modifier.align(Alignment.CenterStart)) {
                Text("<")
            }
            Button({ navBack() }, Modifier.align(Alignment.BottomCenter)) {
                Text("Back")
            }
            Text ("Character Select: $i", Modifier.align(Alignment.TopCenter))
        }
    }

}