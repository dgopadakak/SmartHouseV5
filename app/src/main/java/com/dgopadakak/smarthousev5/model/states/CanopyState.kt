package com.dgopadakak.smarthousev5.model.states

data class CanopyState(
    val ready: Boolean = false,
    val glAviaryReady: Boolean = true,
    val switch1Ready: Boolean = true,
    val switch2Ready: Boolean = true,
    val switch3Ready: Boolean = true,
    val switch4Ready: Boolean = true,
    val switch5Ready: Boolean = true,
    val switch6Ready: Boolean = true,
    val switch7Ready: Boolean = true,

    val name1: String = "",
    val name2: String = "",
    val name3: String = "",
    val name4: String = "",
    val name5: String = "",
    val name6: String = "",
    val name7: String = "",
    val isOn1: Boolean = false,
    val isOn2: Boolean = false,
    val isOn3: Boolean = false,
    val isOn4: Boolean = false,
    val isOn5: Boolean = false,
    val isOn6: Boolean = false,
    val isOn7: Boolean = false,
)
