

import java.util.Map;

import org.apache.struts2.interceptor.SessionAware;



public class UserLogOut implements SessionAware {
	private Map<String, Object> session;

	
	public void setSession(Map map)
	{
		this.session= map;	
	}
    
    public String logOut() {
        session.remove("loggedUser");
        session.clear();
        return "SUCCESS";
    }


}
