package com.examinator.components.maths

import com.examinator.components.maths.ltgt.LtgtSection
import com.examinator.security.authentication.User
import grails.converters.JSON
import grails.plugin.springsecurity.annotation.Secured

@Secured(['ROLE_ADMIN','ROLE_STUDENT'])
class LtgtController {

    def ltgtQuestionService

    def index() { }

    def create(){
        User user = authenticatedUser
        println "user.username = ${user.username}"
        println "user.id = ${user.id}"
        LtgtSection section = ltgtQuestionService.createSection(user)
        render section as JSON
    }
}
