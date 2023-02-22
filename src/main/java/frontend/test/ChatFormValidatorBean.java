package frontend.test;

import jakarta.enterprise.context.RequestScoped;
import jakarta.enterprise.context.SessionScoped;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.component.UIComponent;
import jakarta.faces.context.FacesContext;
import jakarta.faces.validator.ValidatorException;
import jakarta.inject.Named;
import java.io.IOException;
import java.io.Serializable;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Named
@RequestScoped
public class ChatFormValidatorBean implements Serializable {

    private static final String CHAT_LOGIN_URL="chat-login";
    boolean isCustomerIdSet;
    private Matcher matcher;
    // Matches valid email-adderss's format
    private static final Pattern EMAIL_PATTERN = Pattern.compile(
            "([^.@]+)(\\.[^.@]+)*@([^.@]+\\.)+([^.@]+)");

    // Maches X number of digits
    private static final Pattern CUSTOMERID_PATTERN = Pattern.compile("^\\d{6}$");

    // Maches {min,max} number of digits
    private static final Pattern TELEPHONE_PATTERN = Pattern.compile("^[0-9]{1,10}$");

    private static final String EMPTY_ERRRO_MSG = "Fyll i fälten";
    private static final String EMAIL_ERRRO_MSG = "Ogiltig e-postadress";
    private static final String TELEPHONE_ERRRO_MSG = "Ogiltig telefonnummer";
    private static final String CUSTOMERID_ERRRO_MSG = "Ogiltig Kund id";

    public ChatFormValidatorBean() {
        this.isCustomerIdSet = true;
    }

    public void validateCustomerId(FacesContext context, UIComponent component, Object value)
            throws ValidatorException {
        String customer_id_tmp = value.toString();
        matcher = CUSTOMERID_PATTERN.matcher(customer_id_tmp);
        if (customer_id_tmp.isEmpty()) {
            return;
        }
        isCustomerIdSet = false;
        if (!matcher.matches()) {
            FacesMessage msg = new FacesMessage(CUSTOMERID_ERRRO_MSG);
            msg.setSeverity(FacesMessage.SEVERITY_ERROR);
            throw new ValidatorException(msg);
        }
    }

    public void validateEmptyField(FacesContext context, UIComponent component, Object value)
            throws ValidatorException {
        if (!isCustomerIdSet) {
            return;
        }
        if (value.toString().isEmpty()) {
            FacesMessage msg = new FacesMessage(EMPTY_ERRRO_MSG);
            msg.setSeverity(FacesMessage.SEVERITY_ERROR);
            throw new ValidatorException(msg);
        }
    }

    public void validateEmail(FacesContext context, UIComponent component, Object value)
            throws ValidatorException {
        if (!isCustomerIdSet) {
            return;
        }
        matcher = EMAIL_PATTERN.matcher(value.toString());
        if (!matcher.matches()) {
            FacesMessage msg = new FacesMessage(EMAIL_ERRRO_MSG);
            msg.setSeverity(FacesMessage.SEVERITY_ERROR);
            throw new ValidatorException(msg);
        }
    }

    public void validateTelephone(FacesContext context, UIComponent component, Object value)
            throws ValidatorException {
        if (!isCustomerIdSet) {
            return;
        }
        matcher = TELEPHONE_PATTERN.matcher(value.toString());
        if (!matcher.matches()) {
            FacesMessage msg = new FacesMessage(TELEPHONE_ERRRO_MSG);
            msg.setSeverity(FacesMessage.SEVERITY_ERROR);
            throw new ValidatorException(msg);
        }
    }

    public void submit() throws IOException {
        FacesContext.getCurrentInstance().getExternalContext().redirect("test.xhtml");
    }

}
