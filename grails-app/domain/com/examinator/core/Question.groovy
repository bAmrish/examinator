package com.examinator.core

abstract class Question {
    Answer userAnswer
    Answer correctAnswer

    static embedded = ['userAnswer', 'correctAnswer']
    static constraints = {
    }
}
