package com.adwi.neumorph.sample

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.ui.Modifier
import com.adwi.neumorph.components.composables.MorphBackground
import com.adwi.neumorph.sample.ui.screens.HomeScreen
import com.adwi.neumorph.sample.ui.theme.NeuTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            NeuTheme() {
                MorphBackground(Modifier.fillMaxSize()) {
                    HomeScreen()
                }
            }
        }
    }
}
