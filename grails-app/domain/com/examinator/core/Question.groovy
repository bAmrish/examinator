package com.examinator.core

abstract class Question implements Displayable{

    Answer userAnswer

    Answer correctAnswer

    static embedded = ['userAnswer', 'correctAnswer']

    static constraints = {}

    @Override
    Map forDisplay(Map config = null, Question that = this) {
        boolean displayAll = false
        Map questionDisplayMap = [:]

        if(config?.question?.displayAll){
            displayAll = true
        }

        if(displayAll){
            questionDisplayMap["correctAnswer"] = that?.correctAnswer?.forDisplay(config, that.correctAnswer)
        }

        questionDisplayMap["userAnswer"] = that?.userAnswer?.forDisplay(config, that.userAnswer)

        return questionDisplayMap

    }
}
