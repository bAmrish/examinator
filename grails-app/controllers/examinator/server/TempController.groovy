package examinator.server

import grails.converters.JSON


class TempController {

    def tempAccessService

    def index() {
        println("This is index method")
        render "Index method."
    }

    def save() {
        String value = params.value
        boolean flag = params.flag
        Temp temp = tempAccessService.createTemp(value, flag)
        render temp as JSON
    }
}
