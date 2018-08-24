package com.examinator.products.ltgt.domain

import com.examinator.core.Question

class LtgtQuestion extends Question{
    int number1
    int number2

    LtgtAnswer userAnswer

    LtgtAnswer correctAnswer

    @Override
    Map forDisplay(Map config, Question that) {
        Map questionDisplayMap = super.forDisplay(config, that)
        questionDisplayMap["number1"] = number1
        questionDisplayMap["number2"] = number2

        return questionDisplayMap
    }
}
