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
import androidx.compose.material3.Button
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
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
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
                nameList = listOf("a", "BB", "ccc", "DDDD", "eeeee", "FFFFFF", "ggggggg"),
                isOnList = listOf(true, false, false, false, true, true, false)
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
}

@Composable
fun WaterTankCard(
    pinned: MutableState<Int>,
    waterTankState: MutableState<WaterTankState>
) {
    val density = LocalDensity.current
    Card(
        modifier = Modifier.padding(10.dp)
    ) {
        Box(
            modifier = if (pinned.value == 0)
                Modifier.fillMaxWidth()
            else
                Modifier
                    .width(300.dp)
                    .height(140.dp)
        ) {
            if (pinned.value != 0) {
                IconButton(
                    onClick = { pinned.value = 0 },
                    modifier = Modifier.align(Alignment.TopEnd)
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_add_circle_outline_24),
                        contentDescription = "Add button"
                    )
                }
            }
            Column {
                Row {
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
                            fontSize = if (pinned.value == 0)
                                with(density) { 25.dp.toSp() }
                            else
                                with(density) { 15.dp.toSp() }
                        )
                    }
                    if (pinned.value != 0) {
                        Text(
                            text = "Насос ${if (waterTankState.value.isPumpOn)
                                "работает"
                            else
                                "работал"}: " +
                                    "${waterTankState.value.time} мин.",
                            fontSize = with(density) { 15.dp.toSp() },
                            modifier = Modifier.padding(top = 19.dp)
                        )
                    } else {
                        Column(
                            modifier = Modifier.padding(start = 5.dp, top = 15.dp)
                        ) {
                            Text(
                                text = "Насос ${if (waterTankState.value.isPumpOn)
                                    "работает"
                                else
                                    "работал"}:"
                            )
                            Text(text = "${waterTankState.value.time} мин.")
                        }
                    }
                }

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                ) {
                    Text(
                        text = "Режим работы:",
                        fontSize = if (pinned.value == 0)
                            with(density) { 17.dp.toSp() }
                        else
                            with(density) { 15.dp.toSp() },
                        modifier = if (pinned.value == 0)
                            Modifier.padding(start = 12.dp, top = 15.dp)
                        else
                            Modifier.padding(start = 12.dp, top = 5.dp)
                    )
                    if (pinned.value != 0) {
                        Text(
                            text = "Насос:",
                            fontSize = with(density) { 15.dp.toSp() },
                            modifier = Modifier.padding(top = 5.dp, end = 17.dp)
                        )
                    }
                }

                Row(
                    modifier = Modifier.fillMaxWidth().padding(bottom = 10.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        RadioButton(
                            selected = waterTankState.value.mode == 0,
                            onClick = { /*TODO*/ },
                            modifier = Modifier
                                .size(30.dp)
                                .padding(start = 15.dp)
                        )
                        Text(
                            text = "В дом",
                            fontSize = with(density) { 14.dp.toSp() },
                            modifier = Modifier.padding(start = 6.dp)
                        )
                        RadioButton(
                            selected = waterTankState.value.mode == 1,
                            onClick = { /*TODO*/ },
                            modifier = Modifier
                                .size(30.dp)
                                .padding(start = 15.dp)
                        )
                        Text(
                            text = "На улицу",
                            fontSize = with(density) { 14.dp.toSp() },
                            modifier = Modifier.padding(start = 6.dp)
                        )
                    }
                    Row(
                        modifier = Modifier.padding(end = 15.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        if (pinned.value == 0) {
                            Text(
                                text = "Насос:",
                                fontSize = with(density) { 14.dp.toSp() }
                            )
                        }
                        Switch(
                            checked = waterTankState.value.isPumpOn,
                            onCheckedChange = {
                                /*TODO*/
                            },
                            modifier = if (pinned.value == 0)
                                Modifier.padding(start = 10.dp)
                            else
                                Modifier.padding(start = 29.dp)
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun CanopyCard(
    pinned: MutableState<Int>,
    canopyState: MutableState<CanopyState>
) {
    val density = LocalDensity.current
    Card(
        modifier = Modifier.padding(10.dp)
    ) {
        Box(
            modifier = if (pinned.value == 1)
                Modifier.fillMaxWidth()
            else
                Modifier
                    .width(300.dp)
                    .height(140.dp)
        ) {
            if (pinned.value != 1) {
                IconButton(
                    onClick = { pinned.value = 1 },
                    modifier = Modifier.align(Alignment.TopEnd)
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_add_circle_outline_24),
                        contentDescription = "Add button"
                    )
                }
                Row(
                    modifier = Modifier.padding(start = 15.dp, top = 10.dp)
                ) {
                    Column {
                        for (i in 0 until 4) {
                            Row(
                                modifier = Modifier.padding(top = 5.dp)
                            ) {
                                Text(text = "${canopyState.value.nameList[i]}: " +
                                        if (canopyState.value.isOnList[i]) "on" else "off",
                                    fontSize = with(density) { 14.dp.toSp() }
                                )
                            }
                        }
                    }
                    Column(
                        modifier = Modifier.padding(start = 20.dp)
                    ) {
                        for (i in 4 until 7) {
                            Row(
                                modifier = Modifier.padding(top = 5.dp)
                            ) {
                                Text(text = "${canopyState.value.nameList[i]}: " +
                                        if (canopyState.value.isOnList[i]) "on" else "off",
                                    fontSize = with(density) { 14.dp.toSp() }
                                )
                            }
                        }
                    }
                }
                Button(
                    modifier = Modifier
                        .align(Alignment.BottomEnd)
                        .padding(end = 15.dp, bottom = 10.dp),
                    onClick = { /*TODO*/ }
                ) {
                    Text(text = "Свет")
                }
            } else {
                Column {
                    for (i in 0 until 7) {
                        CanopyRow(
                            isReady = canopyState.value.switchReadyList[i],
                            name = canopyState.value.nameList[i],
                            isOn = canopyState.value.isOnList[i],
                            index = i
                        )
                    }
                    Button(
                        onClick = { /*TODO*/ },
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(15.dp)
                    ) {
                        Text(text = "Основной свет")
                    }
                }
            }
        }
    }
}

@Composable
fun CanopyRow(isReady: Boolean, name: String, isOn: Boolean, index: Int) {
    val density = LocalDensity.current
    Row(
        modifier = if (index == 0)
            Modifier
                .fillMaxWidth()
                .padding(start = 25.dp, top = 15.dp)
        else
            Modifier
                .fillMaxWidth()
                .padding(start = 25.dp, top = 5.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center
    ) {
        IconButton(onClick = { /*TODO*/ }) {
            Icon(painter = painterResource(
                id = R.drawable.ic_rename_24),
                contentDescription = "Rename button"
            )
        }
        Text(
            text = name,
            fontSize = with(density) { 17.dp.toSp() }
        )
        Switch(
            checked = isOn,
            onCheckedChange = { /*TODO*/ },
            modifier = Modifier.padding(start = 15.dp)
        )
    }
}

@Composable
fun DishwasherCard(
    pinned: MutableState<Int>,
    dishwasherState: MutableState<DishwasherState>
) {
    Card(
        modifier = Modifier.padding(10.dp)
    ) {
        Box(
            modifier = if (pinned.value == 2)
                Modifier.fillMaxWidth()
            else
                Modifier
                    .width(300.dp)
                    .height(140.dp)
        ) {
            if (pinned.value != 2) {
                IconButton(
                    onClick = { pinned.value = 2 },
                    modifier = Modifier.align(Alignment.TopEnd)
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_add_circle_outline_24),
                        contentDescription = "Add button"
                    )
                }
            }
            Text(
                text = "Coming soon...",
                modifier = Modifier
                    .align(Alignment.Center)
                    .padding(vertical = 40.dp)
            )
        }
    }
}
