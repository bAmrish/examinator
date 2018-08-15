package com.examinator.components.maths

import com.examinator.components.maths.ltgt.LtgtAnswer
import com.examinator.components.maths.ltgt.LtgtQuestion
import com.examinator.components.maths.ltgt.LtgtSection
import com.examinator.components.maths.ltgt.LtgtSectionConfig
import com.examinator.core.QuestionService
import com.examinator.core.Section
import com.examinator.core.SectionConfig
import com.examinator.security.authentication.User
import grails.gorm.transactions.Transactional

@Transactional
class LtgtQuestionService extends QuestionService {

    @Override
    LtgtSection createSection( SectionConfig config = null ){

        LtgtSectionConfig mergedConfig = getMergedConfig(config)

        LtgtSection section = new LtgtSection()
        section.questions = generateQuestions(mergedConfig)
        section.config = mergedConfig

        return section
    }

    private static List<LtgtQuestion> generateQuestions(LtgtSectionConfig config) {

        Random random = new Random(config.seed)

        List<LtgtQuestion> questions = (1..config.totalQuestions).collect {

            LtgtQuestion question = new LtgtQuestion()
            question.number1 = random.nextInt(config.max - config.min + 1) + config.min
            question.number2 = random.nextInt(config.max - config.min + 1) + config.min
            question.correctAnswer = getAnswer(question)

            return question
        }

        return questions
    }

    private static LtgtAnswer getAnswer(LtgtQuestion question){
        LtgtAnswer answer = new LtgtAnswer()

        if(question.number1 < question.number2) {
            answer.value = "<"
        } else if (question.number1 > question.number2) {
            answer.value = ">"
        } else {
            answer.value = "="
        }

        return answer
    }

    private static LtgtSectionConfig getMergedConfig(SectionConfig inConfig){

        LtgtSectionConfig outConfig = new LtgtSectionConfig()

        if(inConfig == null){
            inConfig = new LtgtSectionConfig()
        }

        outConfig.min = inConfig.min ?: 1
        outConfig.max = inConfig.max ?: 10
        outConfig.totalQuestions = inConfig.totalQuestions ?: 10
        outConfig.seed = inConfig.seed ?: getRandomSeed()

        return outConfig
    }

    private static int getRandomSeed(){
        Random random = new Random()
        return random.nextInt()
    }

}
