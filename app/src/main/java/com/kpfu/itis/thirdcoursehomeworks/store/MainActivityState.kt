package com.kpfu.itis.thirdcoursehomeworks.store

data class MainActivityState(
    val isLoading: Boolean = false,
    val firstCount: String? = null,
    val secondCount: String? = null,
    val thirdCount: String? = null,
    val lastEnteredField: Int = 0
)
