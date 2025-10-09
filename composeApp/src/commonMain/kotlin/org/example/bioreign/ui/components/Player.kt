package org.example.bioreign.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import bioreign.composeapp.generated.resources.BioreignTempLogo
import bioreign.composeapp.generated.resources.Res
import bioreign.composeapp.generated.resources.compose_multiplatform
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
                .size(100.dp)
                .offset(
                    //temporary, will swap out for what's needed to calculate player offset on clamp
                    viewModel.getOffsetX(cameraState, mapState).dp,
                    viewModel.getOffsetY(cameraState, mapState).dp
                )
        )

        // Renders attack based on the state passed in
        if (state.attacking) {
            Image(
                painterResource(Res.drawable.BioreignTempLogo),
                null,
                Modifier
                    .offset(state.position.x.dp, state.position.y.dp) // Adjust based on logic
                    .size(100.dp)
            )
        }

        // Renders spell based on the state passed in
        if (state.casting && state.spells.isNotEmpty()) {
            val currentSpell = state.spells[state.currentSpell]
            Image(
                painterResource(getResId(currentSpell.image)),
                currentSpell.name,
                Modifier
                    .offset(state.position.x.dp, state.position.y.dp) // Adjust based on logic
                    .size(currentSpell.size.dp)
            )
        }
    }
}

@Composable
private fun getResId(imageName: String): DrawableResource {
    return when (imageName) {
        "compose_multiplatform" -> Res.drawable.compose_multiplatform
        "BioreignTempLogo" -> Res.drawable.BioreignTempLogo
        // ...
        else -> Res.drawable.compose_multiplatform
    }
}