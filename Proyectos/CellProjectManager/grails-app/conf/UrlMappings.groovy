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
        
        "/mobile/tipoAcontecimiento/"{
            controller = "tipoAcontecimiento"
            action="rest"
        }
        
        "/acontecimientos/$id"{
            controller = "acontecimiento"
            action="rest"
        }
       
        
	
	"/"(view:"/index")
	"500"(view:'/error')
}
}
