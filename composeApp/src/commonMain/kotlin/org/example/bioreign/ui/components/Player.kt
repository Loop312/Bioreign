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
import org.example.bioreign.model.CharacterState
import org.jetbrains.compose.resources.DrawableResource
import org.jetbrains.compose.resources.painterResource

@Composable
fun Player(characterState: CharacterState) {
    Box(Modifier.fillMaxSize()) {
        // Renders character based on the state passed in
        Image(
            painter = painterResource(getResId(characterState.image)),
            contentDescription = characterState.stats.name,
            modifier = Modifier
                .offset(characterState.position.x.dp, characterState.position.y.dp)
                .size(100.dp)
                .align(Alignment.Center)
        )

        // Renders attack based on the state passed in
        if (characterState.attacking) {
            Image(
                painterResource(Res.drawable.BioreignTempLogo),
                null,
                Modifier
                    .offset(characterState.position.x.dp, characterState.position.y.dp) // Adjust based on logic
                    .size(100.dp)
                    .align(Alignment.Center)
            )
        }

        // Renders spell based on the state passed in
        if (characterState.casting && characterState.spells.isNotEmpty()) {
            val currentSpell = characterState.spells[characterState.currentSpell]
            Image(
                painterResource(getResId(currentSpell.image)),
                currentSpell.name,
                Modifier
                    .offset(characterState.position.x.dp, characterState.position.y.dp) // Adjust based on logic
                    .size(currentSpell.size.dp)
                    .align(Alignment.Center)
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