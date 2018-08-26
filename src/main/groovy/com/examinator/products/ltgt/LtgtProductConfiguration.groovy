package com.examinator.products.ltgt

import com.examinator.products.ltgt.domain.LtgtSectionConfig

final class LtgtProductConfiguration {

     static Map getConfigOptions(){
         LtgtSectionConfig defaultSectionConfig = new LtgtSectionConfig()
         defaultSectionConfig.put("min", 1)
         defaultSectionConfig.put("max", 50)
         defaultSectionConfig.put("totalQuestions", 10)

       Map configMap = [
               grade: 1,
               subject : "maths",
               sectionName: "ltgt",
               serviceName: "ltgtQuestionService",
               defaultSectionConfig: defaultSectionConfig
       ]

        return configMap
    }
}
