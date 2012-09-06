package support.secure

class LoginTagLib {
def loginControl = {
		if(session.usuario!=null){                    
			out << "<fieldset class='login'>"                        
                        out << "<div>"
			out << "Hola ${session.usuario} "                        
                        out << """${link(action:"changePassword", controller:"usuario", alt: "Cambiar clave", class: "image"){"<img src=${createLinkTo(dir: "images/taglib", file: "changePassword_2.png")} width='20px' />"}}"""                        
			out << """${link(action:"logout", controller:"authorize"){"<b>Salir</b>"}}"""
			out << "</div></fieldset>"                        
		}else{
                    out << "<fieldset class='login'><div></div></fieldset>"                        
                }
	}
}
