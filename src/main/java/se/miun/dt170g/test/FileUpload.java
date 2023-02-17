package se.miun.dt170g.test;

import jakarta.faces.event.AjaxBehaviorEvent;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Named;
import jakarta.servlet.http.Part;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;

@Named
@ViewScoped
public class FileUpload implements Serializable {

    // Change the path to your uploads absolute path
    private static final String UPLOADS_DIR_PATH = "/home/adde/Desktop/apppro/src/main/webapp/resources/uploads/";
    private String userFileName;
    private Part fileToUpload;

    /**
     * Creates unique image's name and save images inside uploads directory on server.
     *
     */
    public void onFileUpload() {
        if (this.fileToUpload != null) {
            String filename = fileToUpload.getSubmittedFileName();
            userFileName = filename;
            String extension = ".png"; // default extension
            // Split filename and extension
            int index = filename.lastIndexOf('.');
            if (index > 0) {
                extension = filename.substring(index);
                filename = filename.substring(0, index) + "_";
            }
            Path dir = Path.of(UPLOADS_DIR_PATH);
            try (InputStream input = fileToUpload.getInputStream()) {
                // Create unique filename
                Path filePath = Files.createTempFile(dir, filename, extension);
                Files.copy(input, filePath, StandardCopyOption.REPLACE_EXISTING);
                //Here should save filename in database
            } catch (IOException e) {
                System.out.println("===Error While uploading the file");
            }
        }
    }

    public Part getFileToUpload() {
        return fileToUpload;
    }

    public void setFileToUpload(Part fileToUpload) {
        this.fileToUpload = fileToUpload;
    }

    /**
     * Get original file name to be displayed in chat page before submit.
     * @return filename
     */
    public String getUserFileName() {
        return userFileName;
    }
}
