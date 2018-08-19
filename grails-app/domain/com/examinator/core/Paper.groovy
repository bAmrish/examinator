package com.examinator.core

class Paper implements Displayable{
    int grade

    String subject

    List<Section> sections

    static embedded = ['sections']

    static constraints = {}

    @Override
    Map forDisplay(Map config) {

        Map paperDisplayMap = [
            grade: this.grade,
            subject: this.subject,
            sections: []
        ]

        paperDisplayMap.sections = this.sections.collect { section -> section.forDisplay(config) }

        return paperDisplayMap
    }
}
