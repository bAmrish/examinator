package examinator.server

import grails.converters.JSON
import org.springframework.security.access.annotation.Secured

@Secured(['isFullyAuthenticated()'])
class TempController {

    def tempAccessService
    def configLoaderService

    def index() {
        println("This is index method")
        Map configMap = configLoaderService.loadProductConfigurations()
        render configMap as JSON
    }

    def save() {
        String value = params.value
        boolean flag = params.flag
        Temp temp = tempAccessService.createTemp(value, flag)
        render temp as JSON
    }
}
