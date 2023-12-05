package com.dgopadakak.smarthousev5.model.states

data class WaterTankState(
    val percent: Int = 0,
    val mode: Int = 0,
    val time: Int = 0,
    val isPumpOn: Boolean = false,
)
