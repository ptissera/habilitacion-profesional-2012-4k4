package support.secure

class LoginTagLib {
def loginControl = {
		if(session.user!=null){
			out << "<fieldset class='login'>"                        
                        out << "<div>"
			out << "Hola ${session.user.nombre} "                        
			out << """${link(action:"logout", controller:"authorize"){"Logout"}}"""
			out << "</div></fieldset>"                        
		}else{
                    out << "<fieldset class='login'><div></div></fieldset>"                        
                }
	}
}
