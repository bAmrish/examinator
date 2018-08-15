package com.examinator.core.paper

import com.examinator.components.maths.ltgt.LtgtSectionConfig
import com.examinator.core.Paper
import com.examinator.core.QuestionService
import com.examinator.core.SectionConfig

import grails.core.GrailsApplication

import grails.gorm.transactions.Transactional

@Transactional
class QuestionPaperService {

    GrailsApplication grailsApplication

    //This configuration list all the applicable sections types for a given subject for a grade
    private static Map<Integer, Map<String, List<String>>> paperConfigurations = [
            1: [
                    "maths": ["ltgt"]
            ]
    ]

    //This map is a look of the right service bean for a section type.
    private static Map<String, String> beanConfiguration = ["ltgt": "ltgtQuestionService"]

    //This map is look for a getting section configurations for a given grade, subject and type.
    private static Map<Integer, Map<String, Map<String, SectionConfig>>> sectionConfigMap = [
            1: [
                    "maths": [
                            "ltgt": new LtgtSectionConfig([min: 1, max: 50, totalQuestions: 10])
                    ]
            ]
    ]

    Paper getPaper(int grade, String subject) {
        Paper paper = new Paper()
        paper.grade = grade
        paper.subject = subject
        paper.sections = []

        List<String> sectionTypes = getTypes(grade, subject)
        paper.sections = sectionTypes.collect { sectionType ->

            String sectionBeanName = getSectionBeanName(sectionType)
            def sectionServiceBean = (QuestionService)grailsApplication.getMainContext().getBean(sectionBeanName)

            SectionConfig config = getSectionConfiguration(grade, subject, sectionType)
            sectionServiceBean.createSection(config)
        }

        return paper

    }

    private static List<String> getTypes(int grade, String subject){
        return paperConfigurations[grade][subject]
    }

    private static SectionConfig getSectionConfiguration(int grade, String subject, String type){
        return sectionConfigMap[grade][subject][type]
    }

    private static String getSectionBeanName(String type){
        return beanConfiguration[type]
    }
}
