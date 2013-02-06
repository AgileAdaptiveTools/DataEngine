class UrlMappings
{
	static mappings =
	{
		"/about"( view:"/about")
        "/csvSlurper/$id?" ( controller:"csvSlurper", action:"index" )
        "/product/$id?" ( controller:"product", action:"index" )
        "/setting" ( controller:"setting", action:"index" )
        "/source/$feedpath**?" ( controller:"source", action:"index" )

        // note that this pattern only works when path does not contain an extension
        //  which works for REST resource IDs
        "/$controller/$path**?"
                { action = [GET:"get", POST:"post", PUT:"put", DELETE:"delete"] }

        "/$controller/$action/$id?"{
			constraints {
				// apply constraints here
			}
		}

        "/"(view:"/index")
		"500"(view:'/error')
	}
}
