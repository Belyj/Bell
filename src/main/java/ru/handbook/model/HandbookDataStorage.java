package ru.handbook.controller;

import ru.handbook.model.Contact;
import ru.handbook.model.Group;

import java.io.Serializable;
import java.util.List;

/**
 * Created by operator1 on 14.07.2017.
 */
public class HandbookDataStorage implements Serializable {
    private static volatile HandbookDataStorage instance;

    private HandbookDataStorage() {

    }

    public static HandbookDataStorage getInstance() {
        if (instance == null) {
            synchronized (HandbookDataStorage.class) {
                if (instance == null) {
                    instance = new HandbookDataStorage();
                }
            }
        }
        return instance;
    }


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
