package entity;

import jakarta.persistence.*;

import java.sql.Date;
import java.sql.Time;
import java.util.Objects;

@Entity
@Table(name = "Message", schema = "APP", catalog = "")
public class MessageEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "ID")
    private int id;
    @Basic
    @Column(name = "personID")
    private Integer personId;
    @Basic
    @Column(name = "chatID")
    private Integer chatId;
    @Basic
    @Column(name = "TEXT")
    private String text;
    @Basic
    @Column(name = "IMAGE")
    private String image;
    @Basic
    @Column(name = "timeSent")
    private Time timeSent;
    @Basic
    @Column(name = "dateSent")
    private Date dateSent;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Integer getPersonId() {
        return personId;
    }

    public void setPersonId(Integer personId) {
        this.personId = personId;
    }

    public Integer getChatId() {
        return chatId;
    }

    public void setChatId(Integer chatId) {
        this.chatId = chatId;
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

    public Time getTimeSent() {
        return timeSent;
    }

    public void setTimeSent(Time timeSent) {
        this.timeSent = timeSent;
    }

    public Date getDateSent() {
        return dateSent;
    }

    public void setDateSent(Date dateSent) {
        this.dateSent = dateSent;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MessageEntity that = (MessageEntity) o;
        return id == that.id && Objects.equals(personId, that.personId) && Objects.equals(chatId, that.chatId) && Objects.equals(text, that.text) && Objects.equals(image, that.image) && Objects.equals(timeSent, that.timeSent) && Objects.equals(dateSent, that.dateSent);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, personId, chatId, text, image, timeSent, dateSent);
    }
}
