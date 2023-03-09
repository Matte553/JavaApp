package frontend1;

import jakarta.enterprise.context.RequestScoped;
import jakarta.faces.context.FacesContext;
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

    public static void setObjectAttribute(String attribute, Object value){
        getSession().setAttribute(attribute,value);
    }

    public static Object getValue(String attribute){
       return getSession().getAttribute(attribute);
    }

    public static void destroySession(){
        getSession().invalidate();
    }
}
