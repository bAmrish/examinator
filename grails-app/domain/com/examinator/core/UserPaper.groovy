package com.examinator.core

import org.bson.types.ObjectId

class UserPaper {

    ObjectId id
    long userId
    Paper paper

    static embedded = ['paper']
    static constraints = {}
}
