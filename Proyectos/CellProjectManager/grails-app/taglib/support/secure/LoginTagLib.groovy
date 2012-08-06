package support.secure

class LoginTagLib {
def loginControl = {
		if(session.usuario!=null){
			out << "<fieldset class='login'>"                        
                        out << "<div>"
			out << "Hola ${session.usuario} "                        
			out << """${link(action:"logout", controller:"authorize"){"Salir"}}"""
			out << "</div></fieldset>"                        
		}else{
                    out << "<fieldset class='login'><div></div></fieldset>"                        
                }
	}
}
