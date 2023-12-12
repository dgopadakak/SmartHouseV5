package com.dgopadakak.smarthousev5.ui.cards

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
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
import com.dgopadakak.smarthousev5.model.states.CanopyState

@Composable
fun CanopyCardLarge(canopyState: MutableState<CanopyState>) {
    Card(
        modifier = Modifier.padding(10.dp)
    ) {
        Box(
            modifier = Modifier.fillMaxWidth()
        ) {
            Column {
                for (i in 0 until 7) {
                    CanopyRow(
                        isRenameButtonReady = canopyState.value.renameButtonReadyList[i],
                        isSwitchReady = canopyState.value.switchReadyList[i],
                        name = canopyState.value.nameList[i],
                        isOn = canopyState.value.isOnList[i],
                        index = i
                    )
                }
                Button(
                    enabled = canopyState.value.glAviaryReady,
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

@Composable
fun CanopyCardSmall(
    pinned: MutableState<Int>,
    canopyState: MutableState<CanopyState>
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
                enabled = canopyState.value.glAviaryReady,
                modifier = Modifier
                    .align(Alignment.BottomEnd)
                    .padding(end = 15.dp, bottom = 10.dp),
                onClick = { /*TODO*/ }
            ) {
                Text(text = "Свет")
            }
        }
    }
}

@Composable
fun CanopyRow(
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
            enabled = isSwitchReady,
            checked = isOn,
            onCheckedChange = { /*TODO*/ },
            modifier = Modifier.padding(start = 15.dp)
        )
    }
}
