package com.dgopadakak.smarthousev5.ui

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.dgopadakak.smarthousev5.model.states.WaterTankState
import com.dgopadakak.smarthousev5.ui.theme.LightBlue

@Preview(showSystemUi = true)
@Composable
fun MainScreen() {
    val pinned = remember {
        mutableIntStateOf(0)
    }
    val waterTankState = remember {
        mutableStateOf(WaterTankState(
            percent = 65,
            mode = 0,
            time = 17,
            isPumpOn = true
        ))
    }
    WaterTankCard(pinned = pinned, waterTankState = waterTankState)
}

@Composable
fun WaterTankCard(
    pinned: MutableState<Int>,
    waterTankState: MutableState<WaterTankState>
) {
    Card(
        modifier = Modifier.padding(5.dp)
    ) {
        Row(
            modifier = if (pinned.value == 0) Modifier.fillMaxWidth() else Modifier.width(300.dp)
        ) {
            Box(
                modifier = Modifier.padding(15.dp),
                contentAlignment = Alignment.Center
            ) {
                GradientProgressIndicator(
                    progress = 0.01f * waterTankState.value.percent,
                    gradientStart = LightBlue,
                    gradientEnd = Color.Blue,
                    trackColor = Color.Gray,
                    strokeWidth = if (pinned.value == 0) 10.dp else 5.dp,
                    modifier = if (pinned.value == 0) Modifier.size(80.dp) else Modifier.size(45.dp)
                )
                Text(
                    text = "${waterTankState.value.percent}%",
                    modifier = Modifier.align(Alignment.Center),
                    fontSize = if (pinned.value == 0) 25.sp else 15.sp
                )
            }
        }
    }
}

@Preview
@Composable
fun CanopyCard() {

}

@Preview
@Composable
fun DishwasherCard() {

}
