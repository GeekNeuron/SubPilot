package com.geekneuron.subpilot

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import com.geekneuron.subpilot.ui.theme.SubPilotTheme
import com.geekneuron.subpilot.ui.navigation.SubPilotNavHost

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            SubPilotTheme {
                Surface(color = MaterialTheme.colorScheme.background) {
                    SubPilotNavHost()
                }
            }
        }
    }
}
