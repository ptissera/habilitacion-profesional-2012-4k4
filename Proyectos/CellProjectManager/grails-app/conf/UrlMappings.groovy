class UrlMappings {

	static mappings = {
		"/$controller/$action?/$id?"{  constraints {  
            } } 
        
        /* Mapping para login mobile*/
        "/$controller/$action/$nombreUsuario/$clave"{
            
        }
	
	"/"(view:"/index")
	"500"(view:'/error')
}
}
