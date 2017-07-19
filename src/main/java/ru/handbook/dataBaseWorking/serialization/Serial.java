package ru.handbook.dataBaseWorking.serialization;

import ru.handbook.model.Contact;
import ru.handbook.model.Group;

import java.io.Serializable;
import java.util.List;

/**
 * Created by operator1 on 14.07.2017.
 */
public class Serial implements Serializable {
    private List<Contact> contacts;
    private List<Group> groups;

    public List<Contact> getContacts() {
        return contacts;
    }

    public List<Group> getGroups() {
        return groups;
    }

    public void setContacts(List<Contact> incomingContacts) {
        contacts = incomingContacts;
        System.out.println("Contacts: was serialized");
    }

    public void setGroups(List<Group> incomingGroups) {
        groups = incomingGroups;
        System.out.println("Groups: was serialized");
    }
}
