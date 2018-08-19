package com.examinator.core

abstract class SectionConfig implements Displayable{
    @Override
    Map forDisplay(Map config) {
        // To be implemented by concrete classes
        return [:]
    }
}
