package entity;

import jakarta.persistence.*;

import java.util.Objects;

@Entity
@Table(name = "ChatMember", schema = "APP", catalog = "")
public class ChatMemberEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "ID")
    private int id;
    @Basic
    @Column(name = "chatID")
    private Integer chatId;
    @Basic
    @Column(name = "personID")
    private Integer personId;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Integer getChatId() {
        return chatId;
    }

    public void setChatId(Integer chatId) {
        this.chatId = chatId;
    }

    public Integer getPersonId() {
        return personId;
    }

    public void setPersonId(Integer personId) {
        this.personId = personId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ChatMemberEntity that = (ChatMemberEntity) o;
        return id == that.id && Objects.equals(chatId, that.chatId) && Objects.equals(personId, that.personId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, chatId, personId);
    }
}
