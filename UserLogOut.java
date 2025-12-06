package webDevCa2;

import java.util.Map;

import org.apache.struts2.interceptor.SessionAware;

//add correct jar files in properties
public class UserLogOut implements SessionAware {
	
	  private Map<String, Object> session;

	
	    public void setSession(Map<String, Object> session) {
	        this.session = session;
	    }
	    
	    @Override
	    public String logOut() {
	        session.remove("loggedUser");
	        session.clear();
	        return "SUCCESS";
	    }

}
