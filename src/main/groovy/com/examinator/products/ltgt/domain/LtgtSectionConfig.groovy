package com.examinator.products.ltgt.domain

import com.examinator.core.SectionConfig

class LtgtSectionConfig extends SectionConfig {
    int min
    int max
    int totalQuestions
    int seed

    Map <String, Class<?>> getKeyDefinitionMap(){
        return [
            "min": int,
            "max": int,
            "totalQuestions": int,
            "seed": int
        ]
    }

    @Override
    Map forDisplay(Map config) {
        Map sectionConfigMap = super.forDisplay(config)

        boolean displayAll = false

        if(config?.section?.displayAll){
            displayAll = config.section.displayAll
        }

        sectionConfigMap["min"] = min
        sectionConfigMap["max"] = max
        sectionConfigMap["totalQuestions"] = totalQuestions

        if(displayAll){
            sectionConfigMap["seed"] = seed
        }

        return sectionConfigMap

    }
}
