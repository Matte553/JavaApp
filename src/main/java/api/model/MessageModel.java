package api.model;

import java.sql.Timestamp;

public class MessageModel {
    private int id;
    private int chat_id;
    private int person_id;
    private String text;
    private String image;
    private Timestamp timestamp;

    public MessageModel(int id, int chat_id, int person_id, String text, String image, Timestamp timestamp) {
        this.id = id;
        this.chat_id = chat_id;
        this.person_id = person_id;
        this.text = text;
        this.image = image;
        this.timestamp = timestamp;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getChat_id() {
        return chat_id;
    }

    public void setChat_id(int chat_id) {
        this.chat_id = chat_id;
    }

    public int getPerson_id() {
        return person_id;
    }

    public void setPerson_id(int person_id) {
        this.person_id = person_id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Timestamp getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Timestamp timestamp) {
        this.timestamp = timestamp;
    }


}
