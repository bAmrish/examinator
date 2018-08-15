package com.examinator

import com.examinator.security.authentication.User
import grails.converters.JSON
import grails.plugin.springsecurity.annotation.Secured

@Secured("isFullyAuthenticated()")

class UserController {

    def index() {
        render getUserDisplayData((User)authenticatedUser) as JSON
    }

    private static getUserDisplayData(User user){
        return [
                id: user.id,
                username: user.username,
                accountExpired: user.accountExpired,
                accountLocked: user.accountLocked,
                settings: user.settings,
                enabled: user.enabled
        ]
    }
}
