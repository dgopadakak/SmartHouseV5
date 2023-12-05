package com.dgopadakak.smarthousev5

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.dgopadakak.smarthousev5.ui.MainScreen
import com.dgopadakak.smarthousev5.ui.theme.SmartHouseV5Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            SmartHouseV5Theme {
                MainScreen()
//                Surface(
//                    modifier = Modifier.fillMaxSize(),
//                    color = MaterialTheme.colorScheme.background
//                ) {
//
//                }

            }
        }
    }
}
