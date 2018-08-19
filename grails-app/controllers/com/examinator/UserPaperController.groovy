package com.examinator

import com.examinator.core.UserPaper
import com.examinator.security.authentication.User
import grails.converters.JSON
import grails.plugin.springsecurity.annotation.Secured

@Secured(["isFullyAuthenticated()"])
class UserPaperController {

    static allowedMethods = [
            "create": ['PUT'],
            "list": ['GET']
    ]

    def userPaperService

    def create(){
        String subject = request.JSON["subject"]
        User currentUser = (User) authenticatedUser
        UserPaper paper = userPaperService.generateNewPaper(currentUser, subject)
        render paper as JSON
    }

    def find(){
        String paperId = params["paperId"]
        UserPaper paper = userPaperService.getPaper(paperId)
        render paper as JSON
    }

    def list(){
        User currentUser = (User) authenticatedUser
        List<UserPaper> papers = userPaperService.getAllPapers(currentUser)
        Map displayConfig = [
                question : [
                        displayAll: true
                ]
        ]
        List<Map> papersDisplayMap = papers.collect { it.forDisplay(displayConfig) }
        render papersDisplayMap as JSON
    }
}
