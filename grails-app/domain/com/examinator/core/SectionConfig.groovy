package com.examinator.core

import grails.converters.JSON

abstract class SectionConfig implements Displayable{

    abstract Map <String, Class<?>> getKeyDefinitionMap()

    Set<String> getValidKeys() {
        return this.getKeyDefinitionMap().keySet()
    }
    static {
            JSON.registerObjectMarshaller(SectionConfig) { SectionConfig sectionConfig ->
            Map returnValue =[:]
            Set<String> keys = sectionConfig.getValidKeys()
            keys.each { key -> returnValue[key] = sectionConfig[key]}
            return returnValue
        }
    }

    @Override
    Map forDisplay(Map config) {
        // To be implemented by concrete classes
        return [:]
    }
}
