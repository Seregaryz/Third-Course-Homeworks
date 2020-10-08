package com.kpfu.itis.thirdcoursehomeworks

import kotlinx.android.synthetic.main.activity_main.*

class CalculationService {

    fun calculateAnswer(input: String, currentOperator: String): String {
        val operands = input.split(currentOperator)
        if (operands.size == 2 && operands[0] != "" && operands[1] != "") {
            val first = input.split(currentOperator)[0].toDouble()
            val second = input.split(currentOperator)[1].toDouble()
            return when (currentOperator) {
                "+" -> (first + second).toString()
                "-" -> (first - second).toString()
                "x" -> (first * second).toString()
                else -> (first / second).toString()
            }
        } else return "error"
    }

}