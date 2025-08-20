package org.example.bioreign.menus

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.offset
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import bioreign.composeapp.generated.resources.BioreignTempLogo
import bioreign.composeapp.generated.resources.Res
import org.jetbrains.compose.resources.painterResource

class HomeMenu {
    @Composable
    fun open(navModeMenu: () -> Unit, navKeysMenu: () -> Unit, navSettingsMenu: () -> Unit) {
        Box(Modifier.fillMaxSize()) {
            Image(painterResource(Res.drawable.BioreignTempLogo), null, Modifier.align(Alignment.TopCenter).offset(0.dp, 50.dp))
            Column (Modifier.align(Alignment.Center), horizontalAlignment = Alignment.CenterHorizontally) {
                Button(onClick = { navModeMenu() }) {
                    Text("Start Game")
                }
                Button(onClick = { navKeysMenu() }) {
                    Text("Edit Keys")
                }
                Button(onClick = { navSettingsMenu() }) {
                    Text("Settings")
                }
            }
        }
    }

}