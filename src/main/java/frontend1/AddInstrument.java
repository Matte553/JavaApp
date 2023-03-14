package frontend1;

import Entities.InstrumentEntity;
import EntityController.EntityController;
import frontend1.FileUpload;
import jakarta.faces.context.FacesContext;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

@Named
@ViewScoped
// This class is used to add instrument into database
public class AddInstrument implements Serializable {
    private InstrumentEntity instrument = new InstrumentEntity();

    private FileUpload file = new FileUpload();

    //Save image pair server-image-name, original-image-name
    private Map<String, String> uploadedImages = new HashMap<>();

    @Inject
    private EntityController entityController;

    public InstrumentEntity getInstrument() {
        return instrument;
    }

    public void setInstrument(InstrumentEntity instrument) {
        this.instrument = instrument;
    }

    public FileUpload getFile() {
        return file;
    }

    public void setFile(FileUpload file) {
        this.file = file;
    }

    public ArrayList<String> getUploadedImages() {
        return new ArrayList<>(uploadedImages.values());
    }

    // Upload image and save uploaded images in map uploadedImages
    public void onImageUpload() {
        file.onFileUpload();
        uploadedImages.put(file.getServerFileName(), file.getOriginalFileName());
        file.reset();
    }

    // Save instrument with all its images into database, redirect to instrument for review
    public void save() throws IOException {
        instrument = entityController.addInstrument(
                instrument.getType(), instrument.getName(), instrument.getPrice(), instrument.getDescription());
        uploadedImages.keySet().forEach(key -> entityController.addInstrumentPicture(key, instrument.getId()));
        FacesContext.getCurrentInstance().getExternalContext().redirect(
                "instrument.xhtml?instrumentID=" + Integer.toString(instrument.getId()));
    }

}
