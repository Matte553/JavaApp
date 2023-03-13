package frontend1;

import Entities.InstrumentEntity;
import EntityController.EntityController;
import jakarta.enterprise.context.RequestScoped;
import jakarta.enterprise.context.SessionScoped;
import jakarta.faces.context.FacesContext;
import jakarta.inject.Inject;
import jakarta.inject.Named;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;

@Named
@RequestScoped
public class InstrumentManager implements Serializable {

    private InstrumentEntity instrument;

    private static final String CHAT_LINK = "chat-login.xhtml?subject=Reservation&instrumentID=";

    private int instrumentID;

    @Inject
    private EntityController entityController;

    public InstrumentManager() {
        instrument = new InstrumentEntity();
    }

    public InstrumentEntity getInstrument() {
        return instrument;
    }

    public int getInstrumentID() {
        return instrumentID;
    }

    public void setInstrumentID(int instrumentID) {
        this.instrumentID = instrumentID;
    }

    public ArrayList<InstrumentEntity> getInstruments() {
        return entityController.getInstruments();
    }

    public ArrayList<String> getImages() {
        return entityController.getImagesFromInstrumentId(this.instrumentID);
    }

    public String oneImage(int instrumentID) {
        ArrayList<String> images = entityController.getImagesFromInstrumentId(instrumentID);
        if (images.isEmpty()) {
            return "";
        }
        return images.get(0);
    }

    public void onLoad() {
        instrument = entityController.getInstrumentWithID(this.instrumentID);
    }

    public void submit() throws IOException {
        FacesContext.getCurrentInstance().getExternalContext().redirect(CHAT_LINK + instrumentID);
    }
}
