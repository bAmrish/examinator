package com.examinator.core

abstract class Question {
    protected Answer userAnswer
    protected Answer correctAnswer

    static embedded = ['userAnswer', 'correctAnswer']
    static constraints = {
    }
}
