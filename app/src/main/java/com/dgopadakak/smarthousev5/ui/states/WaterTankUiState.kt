package com.dgopadakak.smarthousev5.ui.states

data class WaterTankUiState(
    val ready: Boolean = false,
    val modeReady: Boolean = true,
    val pumpReady: Boolean = true,

    val percent: Int = 0,
    val mode: Int = 0,
    val time: Int = 0,
    val isPumpOn: Boolean = false,
)
