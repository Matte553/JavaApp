package frontend;

import Entities.InstrumentEntity;
import EntityController.EntityController;
import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import java.io.Serializable;
import java.util.ArrayList;

@Named
@RequestScoped
public class InstrumentManager implements Serializable {

    private InstrumentEntity instrument;

    ArrayList<InstrumentEntity> instruments;

    private int instrumentID;

    @Inject
    private EntityController entityController;

    public InstrumentManager(){
        instrument = new InstrumentEntity();
    }

    public InstrumentEntity getInstrument() {
        return instrument;
    }

    public void setInstrument(InstrumentEntity instrument) {
        this.instrument = instrument;
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

    @PostConstruct
    public void init(){ // del later
        instrument = entityController.getInstruments().get(1);
    }

    public ArrayList<String> getImages(){
        return entityController.getImagesFromInstrumentId(1);
    }

    public String getOneImage(int instrumentID){
        return entityController.getImagesFromInstrumentId(instrumentID).get(0);
    }

    public void onLoad(){

    }

}
