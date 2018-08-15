package com.examinator.components.maths

import com.examinator.components.maths.ltgt.LtgtSection
import grails.converters.JSON
import grails.plugin.springsecurity.annotation.Secured

@Secured(['ROLE_ADMIN','ROLE_STUDENT'])
class LtgtController {

    def ltgtQuestionService

    def index() { }

    def create(){
        LtgtSection section = ltgtQuestionService.createSection()
        render section as JSON
    }
}
