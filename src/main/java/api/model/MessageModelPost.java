package api.model;

public class MessageModelPost {

    private int persId;
    private String text;
    private String imageUrl;

    public MessageModelPost(int persId, String text, String imageUrl) {
        this.persId = persId;
        this.text = text;
        this.imageUrl = imageUrl;
    }

    public int getPersId() {
        return persId;
    }

    public void setPersId(int persId) {
        this.persId = persId;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
}
