package frontend1;

import jakarta.servlet.http.Part;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;


public class FileUpload{

    // Change PROJECT_PATH to your project absolute path
    private static final String PROJECT_PATH = "/home/adde/Desktop/apppro";
    private static final String UPLOADS_DIR_PATH = PROJECT_PATH + "/src/main/webapp/resources/uploads/";
    private static final String SNAPSHOT_UPLOADS = PROJECT_PATH + "/target/test-1.0-SNAPSHOT/resources/uploads/";
    private String originalFileName;

    private String serverFileName;

    private Part fileToUpload;

    /**
     * Creates unique image name and save images inside uploads directory on server.
     */
    public void onFileUpload() {
        if (this.fileToUpload != null) {
            String filename = fileToUpload.getSubmittedFileName();
            originalFileName = filename;
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
                // Start, Used in tests only delete it later
                Path copied = Path.of(SNAPSHOT_UPLOADS + filePath.getFileName().toString());
                Files.copy(filePath, copied, StandardCopyOption.REPLACE_EXISTING);
                // End
                serverFileName = filePath.getFileName().toString();
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
     *
     * @return originalFileName
     */
    public String getOriginalFileName() {
        return originalFileName;
    }

    public String getServerFileName() {
        return serverFileName;
    }

    public void reset() {
        this.fileToUpload = null;
        this.serverFileName = "";
        this.originalFileName = "";
    }

}
