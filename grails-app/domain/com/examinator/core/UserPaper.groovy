package com.examinator.core

import org.bson.types.ObjectId

class UserPaper implements Displayable{

    ObjectId id
    long userId
    Paper paper

    static embedded = ['paper']
    static constraints = {}

    @Override
    Map forDisplay(Map config) {
        return [
            id: this.id.toString(),
            userId: this.userId,
            paper: this.paper.forDisplay(config)
        ]
    }
}
