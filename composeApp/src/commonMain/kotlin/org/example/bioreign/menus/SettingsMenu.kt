package org.example.bioreign.menus

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Button
import androidx.compose.material3.Text
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