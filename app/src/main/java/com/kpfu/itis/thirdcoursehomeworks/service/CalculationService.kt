package com.kpfu.itis.thirdcoursehomeworks.service

import io.reactivex.Single

class CalculationService {

    fun calculateValue(
        wrotedCount: Int,
        wrotetIndex: Int,
        currentCount: Int,
        currentIndex: Int
    ): Single<String> {
        var res = ""
        when (wrotetIndex) {
            1 -> {
                return if (currentIndex == 2) {
                    res += "3_"
                    res += wrotedCount + currentCount
                    Single.just(res)
                } else {
                    res += "2_"
                    res += currentCount - wrotedCount
                    Single.just(res)
                }
            }
            2 -> {
                return if (currentIndex == 1) {
                    res += "3_"
                    res += wrotedCount + currentCount
                    Single.just(res)
                } else {
                    res += "1_"
                    res += currentCount - wrotedCount
                    Single.just(res)
                }
            }
            else -> {
                return if (currentIndex == 1) {
                    res += "2_"
                    res += wrotedCount - currentCount
                    Single.just(res)
                } else {
                    res += "1_"
                    res += wrotedCount - currentCount
                    Single.just(res)
                }
            }
        }
    }
}