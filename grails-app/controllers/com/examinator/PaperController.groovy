package com.examinator

import com.examinator.core.Paper
import grails.converters.JSON
import grails.plugin.springsecurity.annotation.Secured

@Secured(["ROLE_ADMIN", "ROLE_STUDENT"])
class PaperController {

    def questionPaperService

    def index() {
        render "Index method."
    }

    def create(){
        int grade = Integer.parseInt(params["grade"].toString())
        String subject = params["subject"]

        if(!grade || grade < 0 || grade > 1 || !subject?.trim()){
            render "Invalid Request. Expected grade and subject parameters."
        }

        Paper paper = questionPaperService.getPaper(grade, subject)
        render paper as JSON
    }
}
