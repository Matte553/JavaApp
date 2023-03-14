package frontend.calendar;

import Entities.PersonEntity;
import EntityController.*;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Named;

import java.io.Serializable;
import java.util.ArrayList;

@Named
@RequestScoped
public class Persons implements Serializable {
    private final EntityController ec;
    private ArrayList<Person> list;
    private int size;

    public Persons() throws Exception {
        this.ec      = new EntityController();
        this.list = createArrayList();
        this.size    = this.list.isEmpty() ? 0 : this.list.size();
    }

    public ArrayList<Person> getList() {
        return this.list;
    }

    public void setList(ArrayList<Person> list) {
        this.list = list;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public int getSize() {
        return this.size;
    }
    private ArrayList<Person> createArrayList() throws Exception {
        ArrayList<PersonEntity> privateList = ec.getPersons();
        ArrayList<Person> result = new ArrayList<>();
        for (PersonEntity pe : privateList) {
            Person temp = new Person(pe);
            result.add(temp);

        }
        return result;
    }

}
