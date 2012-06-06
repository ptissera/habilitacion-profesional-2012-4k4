package support.secure

class LoginTagLib {
def loginControl = {
		if(session.user!=null){
			out << "<fieldset class='login'><div>"
			out << "Hello ${session.user.nombre} "
			out << """${link(action:"logout", controller:"authorize"){"Logout"}}"""
			out << "</div></fieldset>"                        
		}
	}
}
