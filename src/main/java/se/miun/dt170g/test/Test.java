package se.miun.dt170g.test;


import jakarta.enterprise.context.SessionScoped;
import jakarta.faces.context.FacesContext;
import jakarta.inject.Named;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Named
@SessionScoped
public class Test implements Serializable {
    private String name;
    private List<String> messages;

    Test(){
        messages = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void submit() throws IOException {
        if(!name.isEmpty()){
            messages.add(this.name);
        }
        name=null;
        FacesContext.getCurrentInstance().getExternalContext().redirect("chat.xhtml");
    }
}
