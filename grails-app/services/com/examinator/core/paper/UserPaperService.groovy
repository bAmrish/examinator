package com.examinator.core.paper

import com.examinator.core.Answer
import com.examinator.core.Paper
import com.examinator.core.Question
import com.examinator.core.Section
import com.examinator.core.UserPaper
import com.examinator.security.authentication.User

import com.mongodb.client.FindIterable
import grails.core.GrailsApplication

import static com.mongodb.client.model.Filters.*
import org.bson.types.ObjectId

import grails.gorm.transactions.Transactional

@Transactional
class UserPaperService {

    def questionPaperService
    static GrailsApplication grailsApplication

    UserPaper generateNewPaper(User user, String subject) {
        int grade = user.settings.grade
        Paper paper = questionPaperService.getPaper(grade, subject)
        UserPaper userPaper = new UserPaper([userId: user.id, paper: paper, status: PaperStatus.INCOMPLETE])
        userPaper.save()
        return userPaper
    }

    UserPaper getPaper(String paperId) {
        ObjectId id = new ObjectId(paperId)
        def document = UserPaper.collection.find(eq('_id',id)).first()
        UserPaper paper = documentToUserPaperDomain(document)
        return paper
    }

    List<UserPaper> getAllPapers(User user){

        FindIterable iterable = UserPaper.collection.find(eq('userId', user.id))

        List<UserPaper> papers = iterable.limit(10).collect {  documentToUserPaperDomain( it ) }

        return  papers
    }

    private static UserPaper documentToUserPaperDomain(def document){
        UserPaper userPaper = (UserPaper) document
        userPaper.id = new ObjectId(document["_id"].toString())
        def documentSections = document["paper"]["sections"]
        List<Section> sections = documentSections.collect { createSection(it) }

        userPaper.paper.sections = sections
        return userPaper
    }

    private static Section createSection(def sectionDocument){

        String sectionClassName = sectionDocument["__thisClazzName__"]
        Section section = Class.forName(sectionClassName).newInstance(sectionDocument)

        List<Question> questions = section.questions.collect { questionDocument ->
            String questionClassName = questionDocument["__thisClazzName__"]

            def correctAnswerDocument = questionDocument["correctAnswer"]

            if(correctAnswerDocument){
                String correctAnswerClassName = correctAnswerDocument["__thisClazzName__"]

                Answer correctAnswer = Class.forName(correctAnswerClassName).newInstance(correctAnswerDocument)
                questionDocument["correctAnswer"] = correctAnswer
            }

            def userAnswerDocument = questionDocument["userAnswer"]
            if(userAnswerDocument){
                String userAnswerClassName = userAnswerDocument["__thisClazzName__"]

                Answer userAnswer = Class.forName(userAnswerClassName).newInstance(userAnswerDocument)
                questionDocument["userAnswer"] = userAnswer
            }

            Question question =  Class.forName(questionClassName).newInstance(questionDocument)

            return question
        }

        section.questions = questions

        return section

    }
}
