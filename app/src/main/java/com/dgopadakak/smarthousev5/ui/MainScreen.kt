package com.dgopadakak.smarthousev5.ui

import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.dgopadakak.smarthousev5.model.states.CanopyState
import com.dgopadakak.smarthousev5.model.states.DishwasherState
import com.dgopadakak.smarthousev5.model.states.WaterTankState
import com.dgopadakak.smarthousev5.ui.cards.CanopyCard
import com.dgopadakak.smarthousev5.ui.cards.DishwasherCard
import com.dgopadakak.smarthousev5.ui.cards.WaterTankCard

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
