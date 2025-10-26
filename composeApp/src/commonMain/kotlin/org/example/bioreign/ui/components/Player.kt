package org.example.bioreign.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.graphicsLayer
import bioreign.composeapp.generated.resources.BioreignTempLogo
import bioreign.composeapp.generated.resources.Res
import bioreign.composeapp.generated.resources.compose_multiplatform
import bioreign.composeapp.generated.resources.small_stick2
import org.example.bioreign.model.CameraState
import org.example.bioreign.model.CharacterState
import org.example.bioreign.model.MapState
import org.example.bioreign.viewmodel.CharacterViewModel
import org.jetbrains.compose.resources.DrawableResource
import org.jetbrains.compose.resources.painterResource

@Composable
fun Player(
    state: CharacterState,
    //temporary, will swap out for what's needed to calculate player offset on clamp
    //(cameraState and mapState)
    viewModel: CharacterViewModel,
    cameraState: CameraState,
    mapState: MapState
) {
    Box(contentAlignment = Alignment.Center, modifier = Modifier
        .fillMaxSize()
        .graphicsLayer(
            translationX = viewModel.getOffsetX(cameraState, mapState),
            translationY = viewModel.getOffsetY(cameraState, mapState)
        )
    ) {
        // Renders character based on the state passed in
        Image(
            painter = painterResource(getResId(state.image)),
            contentDescription = state.name,
            modifier = Modifier
                .size(pixelToDp(state.hitBox.size.width))
        )

        // Renders attack based on the state passed in
        if (state.attacking) {
            Image(
                painterResource(Res.drawable.BioreignTempLogo),
                null,
                Modifier
                    .size(pixelToDp(state.hitBox.size.width))
            )
        }

        // Renders spell based on the state passed in
        if (state.casting && state.spells.isNotEmpty()) {
            val currentSpell = state.spells[state.currentSpell]
            Image(
                painterResource(getResId(currentSpell.image)),
                currentSpell.name,
                Modifier
                    .size(pixelToDp(state.hitBox.size.width))
            )
        }
    }
}

@Composable
private fun getResId(imageName: String): DrawableResource {
    return when (imageName) {
        "compose_multiplatform" -> Res.drawable.compose_multiplatform
        "BioreignTempLogo" -> Res.drawable.BioreignTempLogo
        "small_stick2" -> Res.drawable.small_stick2
        // ...
        else -> Res.drawable.compose_multiplatform
    }
}