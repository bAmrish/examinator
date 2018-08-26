package com.examinator.core

import grails.gorm.transactions.Transactional
import groovy.io.FileType
import org.grails.io.support.GrailsResourceUtils

@Transactional
class ConfigLoaderService {

    Map allProductConfigMap = [
            grades: [:]
    ]

    Map loadProductConfigurations(){

        log.info "Loading Product Configuration"

        String basePath =  GrailsResourceUtils.GRAILS_APP_DIR

        List<String> fileNames = []

        File dir = new File("$basePath/../src/main/groovy/com/examinator/products")

        dir.eachFileRecurse (FileType.FILES) { file ->
            if(file.name =~ 'ProductConfiguration.groovy$'){
                String baseFilePath = GrailsResourceUtils.GRAILS_APP_DIR + "../src/main/groovy"
                int baseFilePathSize = baseFilePath.size()
                fileNames << file.toPath().toString()[(baseFilePathSize + 2)..-8].replace("/", ".")
            }
        }

        fileNames.each { fileName ->
            def productClass = Class.forName(fileName)
            Map productConfigMap =  productClass.getConfigOptions()
            this.addProductConfiguration(productConfigMap)
            println this.allProductConfigMap.toString()
        }

        return this.allProductConfigMap
    }

    private Map addProductConfiguration(Map productMap){

        if(!productMap){
            throw new NullPointerException("Product configuration map is null. Product map not loaded.")
        }

        String sectionName = productMap["sectionName"]
        if(!sectionName){
            log.error "Product configuration map not loaded. Section name not found."
            return
        }

        String grade = productMap["grade"]
        if(!grade){
            log.error "Error loading product configuration map for type $sectionName. Grade not defined."
            return
        }

        String subject = productMap["subject"]
        if(!subject){
            log.error "Error loading product configuration map for type $sectionName. Subject not defined"
            return
        }

        String serviceName = productMap["serviceName"]
        if(!serviceName){
            log.error "Error loading product configuration map for type $sectionName. Service name not defined."
        }

        SectionConfig defaultSectionConfig = productMap["defaultSectionConfig"]
        if(!defaultSectionConfig){
            log.warn("No section config specified for type $sectionName.")
        }

        Map gradesMap = this.allProductConfigMap["grades"]

        Map gradeMap = gradesMap[grade]

        if(!gradeMap){
            gradeMap = this.allProductConfigMap["grades"][grade] = [:]
        }

        Map subjectMap = (Map)gradeMap[subject]

        if(!subjectMap){
            subjectMap = gradeMap[subject] = [:]
        }

        Map sectionMap = [:]
        subjectMap[sectionName] = sectionMap
        sectionMap["serviceName"] = serviceName
        sectionMap["defaultSectionConfig"] = defaultSectionConfig

        return this.allProductConfigMap
    }

}
