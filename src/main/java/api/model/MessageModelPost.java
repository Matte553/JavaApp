package api.model;

import jakarta.json.bind.annotation.JsonbCreator;
import jakarta.json.bind.annotation.JsonbProperty;

public class MessageModelPost {

    private int fromID;
    private int toID;
    private String text;
    private String imageUrl;

    public MessageModelPost() {};

    public MessageModelPost(int fromID, int toID, String text, String imageUrl) {
        this.fromID = fromID;
        this.toID = toID;
        this.text = text;
        this.imageUrl = imageUrl;
    }

    public int getFromID() {
        return fromID;
    }

    public void setFromID(int fromID) {
        this.fromID = fromID;
    }

    public int getToID() {
        return toID;
    }

    public void setToID(int toID) {
        this.toID = toID;
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
