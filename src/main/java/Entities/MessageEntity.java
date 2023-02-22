package Entities;

import jakarta.persistence.*;

import java.sql.Timestamp;

@Entity
@Table(name = "MESSAGE", schema = "APP")
public class MessageEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "ID", nullable = false)
    private int id;
    @Basic
    @Column(name = "CHAT_ID", nullable = false)
    private int chatId;
    @Basic
    @Column(name = "PERSON_ID", nullable = false)
    private int personId;
    @Basic
    @Column(name = "TEXT", nullable = true, length = 5000)
    private String text;
    @Basic
    @Column(name = "IMAGE", nullable = true, length = 1000)
    private String image;
    @Basic
    @Column(name = "MESSAGE_TIMESTAMP", nullable = true)
    private Timestamp messageTimestamp;

    public MessageEntity() {
    }

    public MessageEntity(Integer personID, Integer chatID, String text, Timestamp timestamp, String imageURL) {
        setPersonId(personID);
        setChatId(chatID);
        setText(text);
        setMessageTimestamp(timestamp);
        setImage(imageURL);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getChatId() {
        return chatId;
    }

    public void setChatId(int chatId) {
        this.chatId = chatId;
    }

    public int getPersonId() {
        return personId;
    }

    public void setPersonId(int personId) {
        this.personId = personId;
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

    public Timestamp getMessageTimestamp() {
        return messageTimestamp;
    }

    public void setMessageTimestamp(Timestamp messageTimestamp) {
        this.messageTimestamp = messageTimestamp;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        MessageEntity that = (MessageEntity) o;

        if (id != that.id) return false;
        if (chatId != that.chatId) return false;
        if (personId != that.personId) return false;
        if (text != null ? !text.equals(that.text) : that.text != null) return false;
        if (image != null ? !image.equals(that.image) : that.image != null) return false;
        if (messageTimestamp != null ? !messageTimestamp.equals(that.messageTimestamp) : that.messageTimestamp != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + chatId;
        result = 31 * result + personId;
        result = 31 * result + (text != null ? text.hashCode() : 0);
        result = 31 * result + (image != null ? image.hashCode() : 0);
        result = 31 * result + (messageTimestamp != null ? messageTimestamp.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "ID: " + id + " CHAT_ID: " + chatId + " PERSON_ID: " + personId + "\n"
                + " TEXT: " + text + " IMAGE: " + image + " MESSAGE_TIMESTAMP: " + messageTimestamp;
    }
}
