package Entities;

import jakarta.persistence.Column;
import jakarta.persistence.Id;

import java.io.Serializable;

public class ChatmemberEntityPK implements Serializable {
    @Column(name = "CHAT_ID")
    @Id
    private int chatId;
    @Column(name = "PERSON_ID")
    @Id
    private int personId;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ChatmemberEntityPK that = (ChatmemberEntityPK) o;

        if (chatId != that.chatId) return false;
        if (personId != that.personId) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = chatId;
        result = 31 * result + personId;
        return result;
    }
}
