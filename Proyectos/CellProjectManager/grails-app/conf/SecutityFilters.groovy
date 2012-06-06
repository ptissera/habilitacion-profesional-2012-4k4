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
                if (!session.user && !actionName.equals('login')) {
                    redirect(controller:"authorize",action: 'login')                    
                }
            }
        }
    }
}

