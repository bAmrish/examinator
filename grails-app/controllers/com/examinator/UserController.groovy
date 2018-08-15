package com.examinator

import grails.converters.JSON
import grails.plugin.springsecurity.annotation.Secured

@Secured("isFullyAuthenticated()")

class UserController {

    def index() {
        render authenticatedUser as JSON
    }
}
