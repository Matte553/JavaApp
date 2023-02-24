package chat;

import jakarta.enterprise.context.RequestScoped;
import jakarta.faces.context.FacesContext;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Named;
import jakarta.servlet.http.HttpSession;

@Named
@RequestScoped
public class SessionManager {
    public static HttpSession getSession() {
        return (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
    }
    public static void setAttribute(String attribute, String value){
        getSession().setAttribute(attribute,value);
    }
    public static String getValue(String attribute){
       return getSession().getAttribute(attribute).toString();
    }

    public static void destroySession(){
        getSession().invalidate();
    }
}
