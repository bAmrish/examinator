package com.examinator.products.ltgt.domain

import com.examinator.core.Section

class LtgtSection extends Section{

    String subject = "maths"
    String type = "ltgt"
    String title = "Less Than and Greater Than"
    String description = "For each of the numbers below write if they are less than, greater than or equal to the other number."

    List<LtgtQuestion> questions

    LtgtSectionConfig config

    static embedded = ['config', 'questions']

    @Override
    boolean validate() {
        return false
    }

    @Override
    Map forDisplay(Map displayConfig, Section that) {
        Map ltgtSectionDisplayMap =  super.forDisplay(displayConfig, that)

        ltgtSectionDisplayMap["config"] = this.config.forDisplay(displayConfig)

        return ltgtSectionDisplayMap
    }
}
