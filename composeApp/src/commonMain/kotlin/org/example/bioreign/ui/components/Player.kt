package org.example.bioreign.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.Dp
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
    Box(Modifier.fillMaxSize(), Alignment.Center) {
        // Renders character based on the state passed in
        Image(
            painter = painterResource(getResId(state.image)),
            contentDescription = state.stats.name,
            modifier = Modifier
                .size(pixelSize(100))
                .graphicsLayer(
                    translationX = viewModel.getOffsetX(cameraState, mapState),
                    translationY = viewModel.getOffsetY(cameraState, mapState)
                )
        )

        // Renders attack based on the state passed in
        if (state.attacking) {
            Image(
                painterResource(Res.drawable.BioreignTempLogo),
                null,
                Modifier
                    .size(pixelSize(100))
                    .graphicsLayer(
                        translationX = viewModel.getOffsetX(cameraState, mapState),
                        translationY = viewModel.getOffsetY(cameraState, mapState)
                    )
            )
        }

        // Renders spell based on the state passed in
        if (state.casting && state.spells.isNotEmpty()) {
            val currentSpell = state.spells[state.currentSpell]
            Image(
                painterResource(getResId(currentSpell.image)),
                currentSpell.name,
                Modifier
                    .size(pixelSize(currentSpell.size))
                    .graphicsLayer(
                        translationX = viewModel.getOffsetX(cameraState, mapState),
                        translationY = viewModel.getOffsetY(cameraState, mapState)
                    )
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

@Composable
fun pixelSize(pixelSize: Int): Dp {
    val density = LocalDensity.current
    // Convert the fixed pixel size (e.g., 100) into its equivalent Dp value
    return with(density) { pixelSize.toDp() }
}