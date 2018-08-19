package com.examinator.core

import grails.gorm.transactions.Transactional
import grails.plugin.springsecurity.SpringSecurityUtils

@Transactional
class UserService {
    private static final ADMIN_ROLE_NAME = "ROLE_ADMIN"

    boolean isAdmin() {
        List authorities = SpringSecurityUtils.getPrincipalAuthorities()
        return SpringSecurityUtils.authoritiesToRoles(authorities).contains(ADMIN_ROLE_NAME)
    }
}
