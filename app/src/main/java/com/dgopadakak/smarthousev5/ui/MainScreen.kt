package com.dgopadakak.smarthousev5.ui

import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.dgopadakak.smarthousev5.R
import com.dgopadakak.smarthousev5.model.states.CanopyState
import com.dgopadakak.smarthousev5.model.states.DishwasherState
import com.dgopadakak.smarthousev5.model.states.WaterTankState
import com.dgopadakak.smarthousev5.ui.theme.LightBlue

@Preview(showSystemUi = true)
@Composable
fun MainScreen() {
    val pinned = remember {
        mutableIntStateOf(0)
    }
    val waterTankState = remember {
        mutableStateOf(
            WaterTankState(
                ready = true,
                percent = 65,
                mode = 0,
                time = 17,
                isPumpOn = true
            )
        )
    }
    val canopyState = remember {
        mutableStateOf(
            CanopyState(
                ready = true,
                name1 = "a", name2 = "bb",
                name3 = "ccc", name4 = "dddd",
                name5 = "eeeee", name6 = "ffffff",
                name7 = "ggggggg",
                isOn1 = true, isOn2 = false,
                isOn3 = false, isOn4 = false,
                isOn5 = true, isOn6 = true,
                isOn7 = false
            )
        )
    }
    val dishwasherState = remember {
        mutableStateOf(
            DishwasherState(
                ready = true,
                idk = 4
            )
        )
    }

    Column {
        when (pinned.intValue) {
            0 -> WaterTankCard(pinned = pinned, waterTankState = waterTankState)
            1 -> CanopyCard(pinned = pinned, canopyState = canopyState)
            2 -> DishwasherCard(pinned = pinned, dishwasherState = dishwasherState)
        }
    }
    Row(
        modifier = Modifier
            .padding(top = 15.dp)
            .horizontalScroll(rememberScrollState())
    ) {
        when (pinned.intValue) {
            0 -> {
                CanopyCard(pinned = pinned, canopyState = canopyState)
                DishwasherCard(pinned = pinned, dishwasherState = dishwasherState)
            }
            1 -> {
                WaterTankCard(pinned = pinned, waterTankState = waterTankState)
                DishwasherCard(pinned = pinned, dishwasherState = dishwasherState)
            }
            2 -> {
                WaterTankCard(pinned = pinned, waterTankState = waterTankState)
                CanopyCard(pinned = pinned, canopyState = canopyState)
            }
        }
    }
}

@Composable
fun WaterTankCard(
    pinned: MutableState<Int>,
    waterTankState: MutableState<WaterTankState>
) {
    Card(
        modifier = Modifier.padding(10.dp)
    ) {
        Column(
            modifier = if (pinned.value == 0)
                Modifier.fillMaxWidth()
            else
                Modifier
                    .width(300.dp)
                    .height(140.dp)
        ) {
            Row(
                horizontalArrangement = if (pinned.value == 0)
                    Arrangement.Start
                else
                    Arrangement.SpaceBetween,
                modifier = if (pinned.value == 0)
                    Modifier.fillMaxWidth()
                else
                    Modifier.width(300.dp)

            ) {
                Box(
                    modifier = if (pinned.value == 0)
                        Modifier.padding(start = 15.dp, top = 15.dp)
                    else
                        Modifier.padding(start = 10.dp, top = 10.dp, end = 15.dp),
                    contentAlignment = Alignment.Center
                ) {
                    GradientProgressIndicator(
                        progress = 0.01f * waterTankState.value.percent,
                        gradientStart = LightBlue,
                        gradientEnd = Color.Blue,
                        trackColor = Color.Gray,
                        strokeWidth = if (pinned.value == 0)
                            15.dp
                        else
                            5.dp,
                        modifier = if (pinned.value == 0)
                            Modifier.size(160.dp)
                        else
                            Modifier.size(45.dp)
                    )
                    Text(
                        text = "${waterTankState.value.percent}%",
                        modifier = Modifier.align(Alignment.Center),
                        fontSize = if (pinned.value == 0) 25.sp else 15.sp
                    )
                }
                Text(
                    text = "Время работы: ${waterTankState.value.time} мин.",
                    fontSize = if (pinned.value == 0) 16.sp else 15.sp,
                    modifier = if (pinned.value == 0)
                        Modifier.padding(vertical = 25.dp)
                    else
                        Modifier.padding(top = 19.dp)
                )
                if (pinned.value != 0) {
                    IconButton(onClick = { /*TODO*/ }) {
                        Icon(
                            painter = painterResource(id = R.drawable.ic_add_circle_outline_24),
                            contentDescription = "Add button"
                        )
                    }
                }
            }

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
            ) {
                Text(
                    text = "Режим работы:",
                    fontSize = if (pinned.value == 0) 17.sp else 15.sp,
                    modifier = if (pinned.value == 0)
                        Modifier.padding(start = 12.dp, top = 15.dp)
                    else
                        Modifier.padding(start = 12.dp, top = 5.dp)
                )
                if (pinned.value != 0) {
                    Text(
                        text = "Насос:",
                        fontSize = 15.sp,
                        modifier = Modifier.padding(top = 5.dp, end = 30.dp)
                    )
                }
            }

            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.padding(bottom = 10.dp)
            ) {
                RadioButton(
                    selected = waterTankState.value.mode == 0,
                    onClick = { /*TODO*/ }
                )
                Text(text = "В дом")
                RadioButton(
                    selected = waterTankState.value.mode == 1,
                    onClick = { /*TODO*/ }
                )
                Text(text = "На улицу")
                if (pinned.value == 0) {
                    Text(
                        text = "Насос:",
                        modifier = Modifier.padding(start = 35.dp)
                    )
                }
                Switch(
                    checked = waterTankState.value.isPumpOn,
                    onCheckedChange = {
                        /*TODO*/
                    },
                    modifier = if (pinned.value == 0)
                        Modifier.padding(start = 15.dp)
                    else
                        Modifier.padding(start = 30.dp)
                )
            }
        }
    }
}

@Composable
fun CanopyCard(
    pinned: MutableState<Int>,
    canopyState: MutableState<CanopyState>
) {

}

@Composable
fun DishwasherCard(
    pinned: MutableState<Int>,
    dishwasherState: MutableState<DishwasherState>
) {

}
