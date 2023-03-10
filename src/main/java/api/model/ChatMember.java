package api.model;
//API
public class ChatMember {
    private int id;
    private int chat_id;
    private int person_id;

    public ChatMember(int id, int chat_id, int person_id) {
        this.id = id;
        this.chat_id = chat_id;
        this.person_id = person_id;
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
}
