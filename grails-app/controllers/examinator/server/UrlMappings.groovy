package examinator.server

class UrlMappings {

    static mappings = {
        "/$controller/$action?/$id?(.$format)?"{
            constraints {
                // apply constraints here
            }
        }

        post "/temp/" (controller: "temp", action: "save")

        get "/user/paper" (controller: "userPaper", action: "list")
        put "/user/paper" (controller: "userPaper", action: "create")
        get "/user/paper/$paperId" (controller: "userPaper", action: "find")

        "/"(view:"/index")
        "500"(view:'/error')
        "404"(view:'/notFound')
    }
}
