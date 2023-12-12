package com.dgopadakak.smarthousev5.ui

import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.dgopadakak.smarthousev5.model.states.CanopyState
import com.dgopadakak.smarthousev5.model.states.DishwasherState
import com.dgopadakak.smarthousev5.model.states.WaterTankState
import com.dgopadakak.smarthousev5.ui.cards.CanopyCardLarge
import com.dgopadakak.smarthousev5.ui.cards.CanopyCardSmall
import com.dgopadakak.smarthousev5.ui.cards.DishwasherCardLarge
import com.dgopadakak.smarthousev5.ui.cards.DishwasherCardSmall
import com.dgopadakak.smarthousev5.ui.cards.WaterTankCardLarge
import com.dgopadakak.smarthousev5.ui.cards.WaterTankCardSmall
import com.dgopadakak.smarthousev5.ui.theme.SmartHouseV5Theme


@Composable
fun MainScreen(
    waterTankState: MutableState<WaterTankState>,
    canopyState: MutableState<CanopyState>,
    dishwasherState: MutableState<DishwasherState>
) {
    val pinned = remember {
        mutableIntStateOf(0)
    }

    Column {
        when (pinned.intValue) {
            0 -> WaterTankCardLarge(waterTankState = waterTankState)
            1 -> CanopyCardLarge(canopyState = canopyState)
            2 -> DishwasherCardLarge(dishwasherState = dishwasherState)
        }
        Row(
            modifier = Modifier
                .padding(top = 15.dp)
                .horizontalScroll(rememberScrollState())
        ) {
            when (pinned.intValue) {
                0 -> {
                    CanopyCardSmall(pinned = pinned, canopyState = canopyState)
                    DishwasherCardSmall(pinned = pinned, dishwasherState = dishwasherState)
                }

                1 -> {
                    WaterTankCardSmall(pinned = pinned, waterTankState = waterTankState)
                    DishwasherCardSmall(pinned = pinned, dishwasherState = dishwasherState)
                }

                2 -> {
                    WaterTankCardSmall(pinned = pinned, waterTankState = waterTankState)
                    CanopyCardSmall(pinned = pinned, canopyState = canopyState)
                }
            }
        }
    }
}

@Preview(showSystemUi = true)
@Composable
fun MainScreenPreview() {
    val waterTankState = remember {
        mutableStateOf(
            WaterTankState(
                ready = true,
                modeReady = true,
                pumpReady = true,
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
                glAviaryReady = true,
                renameButtonReadyList = listOf(true, true, true, true, false, true, true),
                switchReadyList = listOf(true, true, true, true, true, false, true),
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

    SmartHouseV5Theme {
                MainScreen(
                    waterTankState = waterTankState,
                    canopyState = canopyState,
                    dishwasherState = dishwasherState
                )
//                Surface(
//                    modifier = Modifier.fillMaxSize(),
//                    color = MaterialTheme.colorScheme.background
//                ) {
//                    MainScreen(
//                        waterTankState = waterTankState,
//                        canopyState = canopyState,
//                        dishwasherState = dishwasherState
//                    )
//                }
    }
}
