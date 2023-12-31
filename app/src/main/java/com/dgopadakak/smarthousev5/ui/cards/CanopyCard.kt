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
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.dgopadakak.smarthousev5.R
import com.dgopadakak.smarthousev5.ui.states.CanopyUiState
import com.dgopadakak.smarthousev5.ui.theme.LightGray

@Composable
fun CanopyCardLarge(canopyUiState: MutableState<CanopyUiState>) {
    Card(
        modifier = Modifier.padding(10.dp)
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(450.dp)
        ) {
            Column {
                for (i in 0 until 7) {
                    CanopyRow(
                        isRenameButtonReady = canopyUiState.value.renameButtonReadyList[i],
                        isSwitchReady = canopyUiState.value.switchReadyList[i],
                        name = canopyUiState.value.nameList[i],
                        isOn = canopyUiState.value.isOnList[i],
                        index = i
                    )
                }
                Button(
                    enabled = canopyUiState.value.glAviaryReady,
                    onClick = { /*TODO*/ },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(15.dp)
                ) {
                    Text(text = "Основной свет")
                }
            }
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(LightGray)
                    .clickable(enabled = false, onClick = {}),
                contentAlignment = Alignment.Center
            ) {
                Text(text = "Coming soon...")
            }
        }
    }
}

@Composable
fun CanopyCardSmall(
    pinned: MutableState<Int>,
    canopyUiState: MutableState<CanopyUiState>
) {
    Card(
        modifier = Modifier.padding(10.dp)
    ) {
        Box(
            modifier = Modifier
                .width(300.dp)
                .height(140.dp)
        ) {
            Row(
                modifier = Modifier.padding(start = 15.dp, top = 10.dp)
            ) {
                CanopyColumn(
                    startIndex = 0,
                    endIndex = 4,
                    canopyUiState = canopyUiState
                )
                CanopyColumn(
                    startIndex = 4,
                    endIndex = 7,
                    canopyUiState = canopyUiState,
                    modifier = Modifier.padding(start = 20.dp)
                )
            }
            Button(
                enabled = canopyUiState.value.glAviaryReady,
                modifier = Modifier
                    .align(Alignment.BottomEnd)
                    .padding(end = 15.dp, bottom = 10.dp),
                onClick = { /*TODO*/ }
            ) {
                Text(text = "Свет")
            }

            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(LightGray)
                    .clickable(enabled = false, onClick = {}),
                contentAlignment = Alignment.Center
            ) {
                Text(text = "Coming soon...")
            }

            IconButton(
                onClick = { pinned.value = 1 },
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

@Composable
private fun CanopyRow(
    isRenameButtonReady: Boolean,
    isSwitchReady: Boolean,
    name: String,
    isOn: Boolean,
    index: Int
) {
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
        IconButton(
            enabled = isRenameButtonReady,
            onClick = { /*TODO*/ }
        ) {
            Icon(
                painter = painterResource(
                    id = R.drawable.ic_rename_24
                ),
                contentDescription = "Rename button"
            )
        }
        Text(
            text = name,
            fontSize = with(density) { 17.dp.toSp() }
        )
        Switch(
            enabled = isSwitchReady,
            checked = isOn,
            onCheckedChange = { /*TODO*/ },
            modifier = Modifier.padding(start = 15.dp)
        )
    }
}

@Composable
private fun CanopyColumn(
    startIndex: Int,
    endIndex: Int,
    canopyUiState: MutableState<CanopyUiState>,
    modifier: Modifier = Modifier
) {
    val density = LocalDensity.current
    Column(
        modifier = modifier
    ) {
        for (i in startIndex until endIndex) {
            Row(
                modifier = Modifier.padding(top = 5.dp)
            ) {
                Text(text = "${canopyUiState.value.nameList[i]}: " +
                        if (canopyUiState.value.isOnList[i]) "on" else "off",
                    fontSize = with(density) { 14.dp.toSp() }
                )
            }
        }
    }
}
