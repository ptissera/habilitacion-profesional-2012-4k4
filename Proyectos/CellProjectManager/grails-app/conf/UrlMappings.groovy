class UrlMappings {

	static mappings = {
		"/$controller/$action?/$id?"{  constraints {  
            } } 
        
        /* Mapping para login mobile*/
        "/mobile/restLogin/$nombreUsuario/$clave"{
            controller = "authorize"
            action="restLogin"
        }
        
        "/mobile/restLogout"{
            controller = "authorize"
            action="restLogout"
        }
        
        "/mobile/tareas/$id"{
            controller = "tarea"
            action="rest"
        }
        
         "/mobile/sitios/"{
            controller = "sitio"
            action="rest"
        }
        
        "/mobile/tipoAcontecimientos/"{
            controller = "tipoAcontecimiento"
            action="rest"
        }
        
        "/mobile/acontecimientos/$id"{
            controller = "acontecimiento"
            action="rest"
        }
       
        
	
	"/"(view:"/index")
	"500"(view:'/error')
}
}
