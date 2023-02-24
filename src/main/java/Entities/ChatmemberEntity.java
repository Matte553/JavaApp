package Entities;

import jakarta.persistence.*;

@Entity
@Table(name = "CHATMEMBER", schema = "APP")
@IdClass(ChatmemberEntityPK.class)
public class ChatmemberEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "CHAT_ID")
    private int chatId;
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "PERSON_ID")
    private int personId;

    public ChatmemberEntity() {
    }

    public ChatmemberEntity(Integer chatID, Integer personID) {
        setChatId(chatID);
        setPersonId(personID);
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ChatmemberEntity that = (ChatmemberEntity) o;

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
