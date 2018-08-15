package com.examinator.core.paper

import com.examinator.core.Paper
import com.examinator.core.Section
import com.examinator.core.UserPaper
import com.examinator.security.authentication.User

import com.mongodb.client.FindIterable
import static com.mongodb.client.model.Filters.*

import grails.gorm.transactions.Transactional

@Transactional
class UserPaperService {

    def questionPaperService

    UserPaper generateNewPaper(User user, String subject) {
        int grade = user.settings.grade
        Paper paper = questionPaperService.getPaper(grade, subject)
        UserPaper userPaper = new UserPaper([userId: user.id, paper: paper])
        userPaper.save()
        return userPaper
    }

    List<UserPaper> getAllPapers(User user){

        FindIterable iterable = UserPaper.collection.find(eq('userId', user.id))

        List<UserPaper> papers = iterable.limit(10).collect {  documentToUserPaperDomain( it ) }

        return  papers
    }

    private static UserPaper documentToUserPaperDomain(def document){
        UserPaper paper = (UserPaper) document
        return paper
    }
}
