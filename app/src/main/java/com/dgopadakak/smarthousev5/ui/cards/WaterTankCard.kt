package com.dgopadakak.smarthousev5.ui.cards

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.dgopadakak.smarthousev5.R
import com.dgopadakak.smarthousev5.ui.states.WaterTankUiState
import com.dgopadakak.smarthousev5.ui.theme.LightBlue
import com.dgopadakak.smarthousev5.ui.theme.LightGray

@Composable
fun WaterTankCardLarge(
    waterTankUiState: WaterTankUiState,
    onModeChange: (mode: Int) -> Unit,
    onPumpCheckedChange: (isOn: Boolean) -> Unit
) {
    val density = LocalDensity.current
    Card(
        modifier = Modifier.padding(10.dp)
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(270.dp)
        ) {
            Column {
                Row {
                    Box(
                        modifier = Modifier.padding(start = 15.dp, top = 15.dp),
                        contentAlignment = Alignment.Center
                    ) {
                        GradientProgressIndicator(
                            progress = 0.01f * waterTankUiState.percent,
                            gradientStart = LightBlue,
                            gradientEnd = Color.Blue,
                            trackColor = Color.Gray,
                            strokeWidth = 15.dp,
                            modifier = Modifier.size(160.dp)
                        )
                        Text(
                            text = "${waterTankUiState.percent}%",
                            modifier = Modifier.align(Alignment.Center),
                            fontSize = with(density) { 25.dp.toSp() }
                        )
                    }
                    Column(
                        modifier = Modifier.padding(start = 5.dp, top = 15.dp)
                    ) {
                        Text(
                            text = "Насос ${
                                if (waterTankUiState.isPumpOn)
                                    "работает"
                                else
                                    "работал"
                            }:"
                        )
                        Text(text = "${waterTankUiState.time} мин.")
                    }
                }

                Text(
                    text = "Режим работы:",
                    fontSize = with(density) { 17.dp.toSp() },
                    modifier = Modifier.padding(start = 12.dp, top = 15.dp)
                )

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 10.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        RadioButton(
                            enabled = waterTankUiState.modeReady,
                            selected = waterTankUiState.mode == 0,
                            onClick = { onModeChange.invoke(0) },
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
                            enabled = waterTankUiState.modeReady,
                            selected = waterTankUiState.mode == 1,
                            onClick = { onModeChange.invoke(1) },
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
                        Text(
                            text = "Насос:",
                            fontSize = with(density) { 14.dp.toSp() }
                        )
                        Switch(
                            enabled = waterTankUiState.pumpReady,
                            checked = waterTankUiState.isPumpOn,
                            onCheckedChange = { onPumpCheckedChange.invoke(it) },
                            modifier = Modifier.padding(start = 10.dp)
                        )
                    }
                }
            }
            if (!waterTankUiState.ready) {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(LightGray)
                        .clickable(enabled = false, onClick = {}),  // Чтобы не кликалось насквозь
                    contentAlignment = Alignment.Center
                ) {
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        CircularProgressIndicator(
                            modifier = Modifier
                                .width(64.dp)
                            /*TODO: посмотреть цвета:
                            *   color = MaterialTheme.colorScheme.secondary,
                            *   trackColor = MaterialTheme.colorScheme.surfaceVariant*/
                        )
                        Text(
                            text = waterTankUiState.connectionStatus,
                            modifier = Modifier.padding(top = 35.dp),
                            color = Color.White
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun WaterTankCardSmall(
    pinned: MutableState<Int>,
    waterTankUiState: WaterTankUiState,
    onModeChange: (mode: Int) -> Unit,
    onPumpCheckedChange: (isOn: Boolean) -> Unit
) {
    val density = LocalDensity.current
    Card(
        modifier = Modifier.padding(10.dp)
    ) {
        Box(
            modifier = Modifier
                .width(300.dp)
                .height(140.dp)
        ) {
            Column {
                Row {
                    Box(
                        modifier = Modifier.padding(start = 10.dp, top = 10.dp, end = 15.dp),
                        contentAlignment = Alignment.Center
                    ) {
                        GradientProgressIndicator(
                            progress = 0.01f * waterTankUiState.percent,
                            gradientStart = LightBlue,
                            gradientEnd = Color.Blue,
                            trackColor = Color.Gray,
                            strokeWidth = 5.dp,
                            modifier = Modifier.size(45.dp)
                        )
                        Text(
                            text = "${waterTankUiState.percent}%",
                            modifier = Modifier.align(Alignment.Center),
                            fontSize = with(density) { 15.dp.toSp() }
                        )
                    }
                    Text(
                        text = "Насос ${
                            if (waterTankUiState.isPumpOn)
                                "работает"
                            else
                                "работал"
                        }: " +
                                "${waterTankUiState.time} мин.",
                        fontSize = with(density) { 15.dp.toSp() },
                        modifier = Modifier.padding(top = 19.dp)
                    )
                }

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                ) {
                    Text(
                        text = "Режим работы:",
                        fontSize = with(density) { 15.dp.toSp() },
                        modifier = Modifier.padding(start = 12.dp, top = 5.dp)
                    )
                    Text(
                        text = "Насос:",
                        fontSize = with(density) { 15.dp.toSp() },
                        modifier = Modifier.padding(top = 5.dp, end = 17.dp)
                    )
                }

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 10.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        RadioButton(
                            enabled = waterTankUiState.modeReady,
                            selected = waterTankUiState.mode == 0,
                            onClick = { onModeChange.invoke(0) },
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
                            enabled = waterTankUiState.modeReady,
                            selected = waterTankUiState.mode == 1,
                            onClick = { onModeChange.invoke(1) },
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
                    Switch(
                        enabled = waterTankUiState.pumpReady,
                        checked = waterTankUiState.isPumpOn,
                        onCheckedChange = { onPumpCheckedChange.invoke(it) },
                        modifier = Modifier.padding(start = 29.dp, end = 15.dp)
                    )
                }
            }
            if (!waterTankUiState.ready) {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(LightGray)
                        .clickable(enabled = false, onClick = {}),  // Чтобы не кликалось насквозь
                    contentAlignment = Alignment.Center
                ) {
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        CircularProgressIndicator(
                            modifier = Modifier
                                .width(40.dp)
                            /*TODO: посмотреть цвета:
                            *   color = MaterialTheme.colorScheme.secondary,
                            *   trackColor = MaterialTheme.colorScheme.surfaceVariant*/
                        )
                        Text(
                            text = waterTankUiState.connectionStatus,
                            modifier = Modifier.padding(top = 5.dp),
                            color = Color.White
                        )
                    }
                }
            }
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
    }
}
