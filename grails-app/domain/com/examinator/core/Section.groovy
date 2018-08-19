package com.examinator.core

abstract class Section implements Displayable {
    String __thisClazzName__ = this.getClass().getCanonicalName()
    String subject
    String type
    String title
    String description
    List<Question> questions

    Map forDisplay(Map config, Section that = this){

        Map sectionDisplayMap = [ : ]

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
