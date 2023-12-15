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
import com.dgopadakak.smarthousev5.ui.states.CanopyUiState
import com.dgopadakak.smarthousev5.ui.states.DishwasherUiState
import com.dgopadakak.smarthousev5.ui.states.WaterTankUiState
import com.dgopadakak.smarthousev5.ui.cards.CanopyCardLarge
import com.dgopadakak.smarthousev5.ui.cards.CanopyCardSmall
import com.dgopadakak.smarthousev5.ui.cards.DishwasherCardLarge
import com.dgopadakak.smarthousev5.ui.cards.DishwasherCardSmall
import com.dgopadakak.smarthousev5.ui.cards.WaterTankCardLarge
import com.dgopadakak.smarthousev5.ui.cards.WaterTankCardSmall
import com.dgopadakak.smarthousev5.ui.theme.SmartHouseV5Theme


@Composable
fun MainScreen(
    waterTankUiState: MutableState<WaterTankUiState>,
    canopyUiState: MutableState<CanopyUiState>,
    dishwasherUiState: MutableState<DishwasherUiState>
) {
    val pinned = remember {
        mutableIntStateOf(0)
    }

    Column {
        when (pinned.intValue) {
            0 -> WaterTankCardLarge(waterTankUiState = waterTankUiState)
            1 -> CanopyCardLarge(canopyUiState = canopyUiState)
            2 -> DishwasherCardLarge(dishwasherUiState = dishwasherUiState)
        }
        Row(
            modifier = Modifier
                .padding(top = 15.dp)
                .horizontalScroll(rememberScrollState())
        ) {
            when (pinned.intValue) {
                0 -> {
                    CanopyCardSmall(pinned = pinned, canopyUiState = canopyUiState)
                    DishwasherCardSmall(pinned = pinned, dishwasherUiState = dishwasherUiState)
                }

                1 -> {
                    WaterTankCardSmall(pinned = pinned, waterTankUiState = waterTankUiState)
                    DishwasherCardSmall(pinned = pinned, dishwasherUiState = dishwasherUiState)
                }

                2 -> {
                    WaterTankCardSmall(pinned = pinned, waterTankUiState = waterTankUiState)
                    CanopyCardSmall(pinned = pinned, canopyUiState = canopyUiState)
                }
            }
        }
    }
}

@Preview(showSystemUi = true)
@Composable
fun MainScreenPreview() {
    val waterTankUiState = remember {
        mutableStateOf(
            WaterTankUiState(
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
    val dishwasherUiState = remember {
        mutableStateOf(
            DishwasherUiState(
                ready = true,
                idk = 4
            )
        )
    }

    SmartHouseV5Theme {
        MainScreen(
            waterTankUiState = waterTankUiState,
            canopyUiState = canopyUiState,
            dishwasherUiState = dishwasherUiState
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
