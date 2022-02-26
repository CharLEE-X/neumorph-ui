package com.adwi.neumorph.sample

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.ui.Modifier
import com.adwi.neumorph.android.components.MorphBackground
import com.adwi.neumorph.android.theme.MorphUiTheme
import com.adwi.neumorph.sample.ui.screens.HomeScreen

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MorphUiTheme() {
                MorphBackground(Modifier.fillMaxSize()) {
                    HomeScreen()
                }
            }
        }
    }
}
