package com.kpfu.itis.thirdcoursehomeworks.service

import io.reactivex.Single
import java.util.concurrent.TimeUnit

class CalculationService {

    fun calculateValue(
        wroteCount: Int,
        wroteIndex: Int,
        currentCount: Int,
        currentIndex: Int
    ): Single<MutableList<String>> {
        val res = mutableListOf("", "", "")
        when (wroteIndex) {
            1 -> {
                return if (currentIndex == 2) {
                    res[0] = wroteCount.toString()
                    res[1] = currentCount.toString()
                    res[2] = (wroteCount + currentCount).toString()
                    Single.just(res)
                        .delay(3, TimeUnit.SECONDS)
                } else {
                    res[0] = wroteCount.toString()
                    res[1] = (currentCount - wroteCount).toString()
                    res[2] = currentCount.toString()
                    Single.just(res)
                        .delay(3, TimeUnit.SECONDS)
                }
            }
            2 -> {
                return if (currentIndex == 1) {
                    res[0] = currentCount.toString()
                    res[1] = wroteCount.toString()
                    res[2] = (wroteCount + currentCount).toString()
                    Single.just(res)
                        .delay(3, TimeUnit.SECONDS)
                } else {
                    res[0] = (currentCount - wroteCount).toString()
                    res[1] = wroteCount.toString()
                    res[2] = currentCount.toString()
                    Single.just(res)
                        .delay(3, TimeUnit.SECONDS)
                }
            }
            else -> {
                return if (currentIndex == 1) {
                    res[0] = currentCount.toString()
                    res[1] = (wroteCount - currentCount).toString()
                    res[2] = wroteCount.toString()
                    Single.just(res)
                        .delay(3, TimeUnit.SECONDS)
                } else {
                    res[0] = (wroteCount - currentCount).toString()
                    res[1] = currentCount.toString()
                    res[2] = wroteCount.toString()
                    Single.just(res)
                        .delay(3, TimeUnit.SECONDS)
                }
            }
        }
    }
}