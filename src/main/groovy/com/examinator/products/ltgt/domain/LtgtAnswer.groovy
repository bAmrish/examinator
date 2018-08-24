package com.examinator.products.ltgt.domain

import com.examinator.core.Answer

class LtgtAnswer extends  Answer{

    String value

    @Override
    Map forDisplay(Map config, Answer that) {
        return super.forDisplay(config, that)
    }
}
