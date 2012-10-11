/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author tisserap
 */
class SecutityFilters {
	 def filters = {
        loginCheck(controller: '*', action: '*') {
            before = {
                if (!session.usuario && !actionName.equals('login') && !actionName.equals('restLogin') ) {
                    if (actionName.equals('restLogin'))
                        redirect(controller:"authorize",action: 'restLogin')
                    else
                        redirect(controller:"authorize",action: 'login')                    
                    
                }
            }
        }
    }
}

