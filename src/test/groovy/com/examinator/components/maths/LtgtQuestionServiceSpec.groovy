package com.examinator.components.maths

import com.examinator.components.maths.ltgt.LtgtAnswer
import com.examinator.components.maths.ltgt.LtgtQuestion
import com.examinator.components.maths.ltgt.LtgtSection
import com.examinator.components.maths.ltgt.LtgtSectionConfig
import com.examinator.core.SectionConfig
import grails.testing.gorm.DomainUnitTest
import grails.testing.services.ServiceUnitTest
import spock.lang.Specification

class LtgtQuestionServiceSpec
        extends Specification
        implements ServiceUnitTest<LtgtQuestionService>, DomainUnitTest<LtgtSection> {

    def setup() {
    }

    def cleanup() {
    }

    void "getAnswer() should number 1 is less than number 2"() {
        when:
            LtgtQuestion question = new LtgtQuestion()
            question.number1 = 5
            question.number2 = 10
            LtgtAnswer answer = LtgtQuestionService.getAnswer(question)

        then:
            answer.value == "<"
    }

    void "getAnswer() should number 1 is greater than number 2"() {
        when:
            LtgtQuestion question = new LtgtQuestion()
            question.number1 = 15
            question.number2 = 10
            LtgtAnswer answer = LtgtQuestionService.getAnswer(question)

        then:
            answer.value == ">"
    }

    void "getAnswer() should number 1 is equal to number 2"() {
        when:
            LtgtQuestion question = new LtgtQuestion()
            question.number1 = 10
            question.number2 = 10
            LtgtAnswer answer = LtgtQuestionService.getAnswer(question)

        then:
            answer.value == "="
    }

    void "getMergedConfig() should give default values for null input"(){
        when:
            LtgtSectionConfig config = LtgtQuestionService.getMergedConfig()
        then:
            config.min == 1
            config.max == 10
            config.totalQuestions == 10
            config.seed != null
            (Object)config.seed instanceof Integer
    }

    void "getMergedConfig() should give override values for given input"(){
        when:
            LtgtSectionConfig inputConfig = new LtgtSectionConfig([min:10, max: 50, totalQuestions: 100, seed: 200])
            LtgtSectionConfig config = LtgtQuestionService.getMergedConfig(inputConfig)

        then:
            config.min == 10
            config.max == 50
            config.totalQuestions == 100
            config.seed == 200
    }

    void "getMergedConfig() should give override values for given input with missing seed"(){
        when:
        LtgtSectionConfig inputConfig = new LtgtSectionConfig([min:10, max: 50, totalQuestions: 100])
        LtgtSectionConfig config = LtgtQuestionService.getMergedConfig(inputConfig)

        then:
        config.min == 10
        config.max == 50
        config.totalQuestions == 100
        config.seed != null
        (Object)config.seed instanceof Integer
    }

    void "createSection() should should generate the right number of questions with given config"(){
        when:
            LtgtSectionConfig config = new LtgtSectionConfig([min: 0, max: 10, totalQuestions: 2])
            LtgtSection section = service.createSection(config)
        then:
            section.questions.size() == 2
    }

    void "createSection() should should generate the right questions with given config"(){

        LtgtSectionConfig config = new LtgtSectionConfig([min: 0, max: 10, totalQuestions: 2])
        LtgtSection section = service.createSection(config)
        expect:
            section.questions[0].number1 >= 0
            section.questions[0].number2 >= 0
            section.questions[0].number1 <= 10
            section.questions[0].number2 <= 10
            section.questions[1].number1 >= 0
            section.questions[1].number2 >= 0
            section.questions[1].number1 <= 10
            section.questions[1].number2 <= 10

    }

    void "createSection() should generate the same questions with the same seed"() {
        when:
            LtgtSectionConfig config1 = new LtgtSectionConfig([seed: 100])
            LtgtSectionConfig config2 = new LtgtSectionConfig([seed: 100])
            LtgtSection section1 = service.createSection(config1)
            LtgtSection section2 = service.createSection(config2)

        then:
            section1.questions.size() == section2.questions.size()
            section1.questions[0].number1 == section2.questions[0].number1
            section1.questions[0].number2 == section2.questions[0].number2
            section1.questions[1].number1 == section2.questions[1].number1
            section1.questions[1].number2 == section2.questions[1].number2
            section1.questions[2].number1 == section2.questions[2].number1
            section1.questions[2].number2 == section2.questions[2].number2
            section1.questions[3].number1 == section2.questions[3].number1
            section1.questions[3].number2 == section2.questions[3].number2
            section1.questions[4].number1 == section2.questions[4].number1
            section1.questions[4].number2 == section2.questions[4].number2
            section1.questions[5].number1 == section2.questions[5].number1
            section1.questions[5].number2 == section2.questions[5].number2
            section1.questions[6].number1 == section2.questions[6].number1
            section1.questions[6].number2 == section2.questions[6].number2
            section1.questions[7].number1 == section2.questions[7].number1
            section1.questions[7].number2 == section2.questions[7].number2
            section1.questions[8].number1 == section2.questions[8].number1
            section1.questions[8].number2 == section2.questions[8].number2
            section1.questions[9].number1 == section2.questions[9].number1
            section1.questions[9].number2 == section2.questions[9].number2

    }
}
