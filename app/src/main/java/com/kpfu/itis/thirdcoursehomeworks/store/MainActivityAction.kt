package com.kpfu.itis.thirdcoursehomeworks.store

sealed class MainActivityAction {
    class CountWrote(val wroteCount: Int?, val index: Int): MainActivityAction()
    class CalculationFinished(val firstCount: Int?, val secondCount: Int?, val thirdCount: Int?): MainActivityAction()
    object CalculationStarted: MainActivityAction()
}