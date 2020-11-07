package com.kpfu.itis.thirdcoursehomeworks.store

data class MainActivityState(
    val isLoading: Boolean = false,
    var firstCount: Int? = null,
    var secondCount: Int? = null,
    var thirdCount: Int? = null,
    var lastIndex: Int = 0,
    var preLastIndex: Int = 0
)
