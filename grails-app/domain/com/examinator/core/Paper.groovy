package com.examinator.core

import org.bson.types.ObjectId

class Paper implements Displayable{

    ObjectId id

    int grade

    String subject

    List<Section> sections

    static embedded = ['sections']

    static constraints = {}

    static mapping = {
        id: attr:"_id"
    }

    Paper(){
        this.id = new ObjectId()
    }

    @Override
    Map forDisplay(Map config) {

        Map paperDisplayMap = [
            id: this.id?.toString(),
            grade: this.grade,
            subject: this.subject,
            sections: []
        ]

        paperDisplayMap.sections = this.sections.collect { section -> section.forDisplay(config) }

        return paperDisplayMap
    }
}
