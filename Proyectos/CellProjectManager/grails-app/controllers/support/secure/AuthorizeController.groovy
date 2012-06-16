package support.secure

class AuthorizeController {

    def index() {
        //		redirect(action: "login")
    }
	
    def login = {    if (session.user) {
            redirect(uri:"/")                    
        }
    }
  
    def authenticate = {
        def user = User.findByUserNameAndPassword(params.userName, params.password)
        if(user){
            session.user = user                  
        }else{
            flash.message = "Sorry, ${params.userName}. Please try again."     
        }
    }
  
    def logout = {
        flash.message = "Goodbye ${session.user.nombre} ${session.user.apellido}"
        session.user = null
        redirect(action: "login")
    }  
}
