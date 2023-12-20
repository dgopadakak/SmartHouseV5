package com.dgopadakak.smarthousev5.ui

import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.dgopadakak.smarthousev5.ui.cards.CanopyCardLarge
import com.dgopadakak.smarthousev5.ui.cards.CanopyCardSmall
import com.dgopadakak.smarthousev5.ui.cards.DishwasherCardLarge
import com.dgopadakak.smarthousev5.ui.cards.DishwasherCardSmall
import com.dgopadakak.smarthousev5.ui.cards.WaterTankCardLarge
import com.dgopadakak.smarthousev5.ui.cards.WaterTankCardSmall
import com.dgopadakak.smarthousev5.ui.states.CanopyUiState
import com.dgopadakak.smarthousev5.ui.viewmodels.WaterTankViewModel


@Composable
fun MainScreen(
    waterTankViewModel: WaterTankViewModel = viewModel(),
    canopyUiState: MutableState<CanopyUiState>,
) {
    val waterTankUiState by waterTankViewModel.waterTankUiState.collectAsState()

    val pinned = remember {
        mutableIntStateOf(0)
    }

    Column {
        when (pinned.intValue) {
            0 -> WaterTankCardLarge(
                waterTankUiState = waterTankUiState,
                onModeChange = { mode -> waterTankViewModel.changeMode(mode) }
            )
            1 -> CanopyCardLarge(canopyUiState = canopyUiState)
            2 -> DishwasherCardLarge(/*dishwasherUiState = dishwasherUiState*/)
        }
        Row(
            modifier = Modifier
                .padding(top = 15.dp)
                .horizontalScroll(rememberScrollState())
        ) {
            when (pinned.intValue) {
                0 -> {
                    CanopyCardSmall(pinned = pinned, canopyUiState = canopyUiState)
                    DishwasherCardSmall(pinned = pinned/*, dishwasherUiState = dishwasherUiState*/)
                }

                1 -> {
                    WaterTankCardSmall(
                        pinned = pinned,
                        waterTankUiState = waterTankUiState,
                        onModeChange = { mode -> waterTankViewModel.changeMode(mode) }
                    )
                    DishwasherCardSmall(pinned = pinned/*, dishwasherUiState = dishwasherUiState*/)
                }

                2 -> {
                    WaterTankCardSmall(
                        pinned = pinned,
                        waterTankUiState = waterTankUiState,
                        onModeChange = { mode -> waterTankViewModel.changeMode(mode) }
                    )
                    CanopyCardSmall(pinned = pinned, canopyUiState = canopyUiState)
                }
            }
        }
    }
}
