package org.example.bioreign.menus

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable

class SettingsMenu {
    @Composable
    fun Open(navBack: () -> Unit) {
        Box {
            Column {
                Button(onClick = { navBack() }) {
                    Text("Back")
                }
            }
        }
    }
}