package com.examinator.core

import org.bson.types.ObjectId

abstract class Section implements Displayable {
    ObjectId id
    String __thisClazzName__ = this.getClass().getCanonicalName()
    String subject
    String type
    String title
    String description
    List<Question> questions

    Section(){
        this.id = new ObjectId()
    }
    static mapping = {
        id: attr: "_id"
    }
    abstract boolean validate()

    Map forDisplay(Map config, Section that = this){

        Map sectionDisplayMap = [ : ]

        sectionDisplayMap["id"]          = that.id?.toString()
        sectionDisplayMap["subject"]     = that.subject
        sectionDisplayMap["type"]        = that.type
        sectionDisplayMap["title"]       = that.title
        sectionDisplayMap["description"] = that.description
        sectionDisplayMap["questions"]   = that.questions.collect {
            it.forDisplay(config, it)
        }

        return sectionDisplayMap
    }
}
