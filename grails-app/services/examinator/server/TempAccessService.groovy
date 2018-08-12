package examinator.server

import grails.gorm.transactions.Transactional

@Transactional
class TempAccessService {

    def createTemp(String value, boolean flag){
        Temp temp = new Temp([value: value, flag: flag])
        temp.save()
        return temp
    }
    def getTemp() {

    }
}
