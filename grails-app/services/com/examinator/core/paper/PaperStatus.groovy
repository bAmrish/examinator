package com.examinator.core.paper

import com.examinator.core.Displayable

enum PaperStatus{
    INCOMPLETE(1), VALIDATED(2)

    private final int status

    PaperStatus(int status){
        this.status = status
    }

}
