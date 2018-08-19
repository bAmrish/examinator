package com.examinator.core

abstract class Answer implements Displayable{

    def value

    static constraints = {}

    @Override
    Map forDisplay(Map config = null, Answer that = this) {
        return [
            value: that?.value
        ]
    }
}
