package com.examinator.products.ltgt

import com.examinator.products.ltgt.domain.LtgtSectionConfig

final class LtgtProductConfiguration {

     static Map getConfigOptions(){

       Map configMap = [
               grade: 1,
               subject : "maths",
               sectionName: "ltgt",
               serviceName: "ltgtQuestionService",
               defaultSectionConfig: new LtgtSectionConfig([min: 1, max: 50, totalQuestions: 10])
       ]

        return configMap
    }
}
