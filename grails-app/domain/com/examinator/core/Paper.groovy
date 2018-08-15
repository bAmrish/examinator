package com.examinator.core

class Paper {
    int grade
    String subject

    List<Section> sections

    static embedded = ['sections']

    static constraints = {}
}
