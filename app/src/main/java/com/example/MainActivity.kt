package com.example

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import com.example.ui.theme.MyApplicationTheme
import com.example.ui.viewmodel.RosaryViewModel

class MainActivity : ComponentActivity() {

    private val viewModel: RosaryViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val uiState by viewModel.uiState.collectAsState()
            MyApplicationTheme(
                themeMode = uiState.themeMode,
                liturgicalColor = uiState.liturgicalColor
            ) {
                com.example.ui.screens.MainAppScreen(viewModel = viewModel)
            }
        }
    }
}
