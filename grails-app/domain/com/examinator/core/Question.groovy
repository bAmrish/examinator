package com.examinator.core

import org.bson.types.ObjectId

abstract class Question implements Displayable{

    String __thisClazzName__ = this.getClass().getCanonicalName()

    ObjectId id

    Question() {
        this.id = new ObjectId()
    }

    Answer userAnswer

    Answer correctAnswer

    static embedded = ['userAnswer', 'correctAnswer']

    static constraints = {}

    static mapping = {
        id: attr:"_id"
    }

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

        questionDisplayMap["id"] = that?.id?.toString()
        questionDisplayMap["userAnswer"] = that?.userAnswer?.forDisplay(config, that.userAnswer)

        return questionDisplayMap

    }
}
