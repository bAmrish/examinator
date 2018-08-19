package com.examinator.components.maths.ltgt

import com.examinator.core.SectionConfig

class LtgtSectionConfig extends SectionConfig {
    int min
    int max
    int totalQuestions
    int seed

    static constraints = {}

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
