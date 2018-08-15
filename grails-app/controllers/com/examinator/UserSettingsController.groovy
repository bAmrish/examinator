package com.examinator

import com.examinator.security.authentication.User
import examinator.server.UserSettings
import grails.converters.JSON
import org.springframework.security.access.annotation.Secured

@Secured("isFullyAuthenticated()")
class UserSettingsController {

    def index() {
        render (authenticatedUser?.settings ?: []) as JSON
    }

    def save(){
        int grade = Integer.parseInt(params["grade"].toString())
        User currentUser = (User) authenticatedUser
        UserSettings settings = currentUser.settings ?: new UserSettings()
        settings.grade = grade
        currentUser.settings = settings
        currentUser.save()
        render currentUser as JSON
    }
}
