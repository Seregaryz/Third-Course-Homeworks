package com.kpfu.itis.thirdcoursehomeworks.store

data class MainActivityState(
    val isLoading: Boolean = false,
    val counts: MutableList<String>? = null,
    val lastEnteredField: Int = 0
)
