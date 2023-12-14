package com.dgopadakak.smarthousev5.ui.states

data class CanopyState(
    val ready: Boolean = false,
    val glAviaryReady: Boolean = true,
    val renameButtonReadyList: List<Boolean> = listOf(true, true, true, true, true, true, true),
    val switchReadyList: List<Boolean> = listOf(true, true, true, true, true, true, true),
    val nameList: List<String> = listOf("", "", "", "", "", "", ""),
    val isOnList: List<Boolean> = listOf(false, false, false, false, false, false, false)
)
