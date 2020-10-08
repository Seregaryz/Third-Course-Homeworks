package com.kpfu.itis.thirdcoursehomeworks

import com.google.common.truth.Truth.assertThat
import org.junit.Before
import org.junit.Test


class CalculatorTest {

    private lateinit var service: CalculationService

    @Before
    fun setUp(){
        service = CalculationService()
    }

    @Test
    fun checkThenPlus(){
        assertThat(service.calculateAnswer("25+36", "+")).isEqualTo("61.0")
    }

    @Test
    fun checkThenMinus(){
        assertThat(service.calculateAnswer("25-12", "-")).isEqualTo("13.0")
    }

    @Test
    fun checkThenMultiply(){
        assertThat(service.calculateAnswer("25x5", "x")).isEqualTo("125.0")
    }

    @Test
    fun checkThenDivision(){
        assertThat(service.calculateAnswer("25/5", "/")).isEqualTo("5.0")
    }

    @Test
    fun checkThatError(){
        assertThat(service.calculateAnswer("24+", "+")).isEqualTo("error")
    }

}