package com.examinator.core

import com.examinator.core.paper.PaperStatus
import org.bson.types.ObjectId

class UserPaper implements Displayable{

    ObjectId id
    long userId
    PaperStatus status
    Paper paper

    static embedded = ['paper']
    static constraints = {}

    @Override
    Map forDisplay(Map config) {
        return [
            id: this.id.toString(),
            userId: this.userId,
            status: this.status,
            paper: this.paper.forDisplay(config)
        ]
    }
}
