package org.sluman.mercedes

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import dagger.hilt.android.AndroidEntryPoint
import org.sluman.mercedes.presentation.ui.MainAppScreen
import org.sluman.mercedes.presentation.ui.theme.MercedesTheme

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MercedesTheme {
                MainAppScreen()
            }
        }
    }
}
