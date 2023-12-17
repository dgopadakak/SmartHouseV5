package com.dgopadakak.smarthousev5

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import com.dgopadakak.smarthousev5.ui.MainScreen
import com.dgopadakak.smarthousev5.ui.states.CanopyUiState
import com.dgopadakak.smarthousev5.ui.theme.SmartHouseV5Theme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            // temporal
            val canopyUiState = remember {
                mutableStateOf(
                    CanopyUiState(
                        ready = true,
                        glAviaryReady = true,
                        renameButtonReadyList = listOf(true, true, true, true, false, true, true),
                        switchReadyList = listOf(true, true, true, true, true, false, true),
                        nameList = listOf("a", "BB", "ccc", "DDDD", "eeeee", "FFFFFF", "ggggggg"),
                        isOnList = listOf(true, false, false, false, true, true, false)
                    )
                )
            }

            SmartHouseV5Theme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    MainScreen(canopyUiState = canopyUiState)
                }
            }
        }
    }
}
