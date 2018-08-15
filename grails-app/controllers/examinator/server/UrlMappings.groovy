package examinator.server

class UrlMappings {

    static mappings = {
        "/$controller/$action?/$id?(.$format)?"{
            constraints {
                // apply constraints here
            }
        }

        post "/temp/" (controller: "temp", action: "save")
        "/user/paper/$action" (controller: "userPaper")

        "/"(view:"/index")
        "500"(view:'/error')
        "404"(view:'/notFound')
    }
}
