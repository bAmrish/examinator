package com.examinator.core

abstract class Section {
    String __thisClazzName__ = this.getClass().getCanonicalName()
    String subject
    String type
    String title
    String description
    List<Question> questions
}
