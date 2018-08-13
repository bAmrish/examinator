package com.examinator.components.maths.ltgt

import com.examinator.core.Section

class LtgtSection extends Section{

    String title = "Less Than and Greater Than"
    String description = "For each of the numbers below write if they are less than, greater than or equal to the other number."
    List<LtgtQuestion> questions
    LtgtSectionConfig config

    static embedded = ['config']

    static constraints = {}
}
