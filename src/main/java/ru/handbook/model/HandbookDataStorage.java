package ru.handbook.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by operator1 on 14.07.2017.
 */
public class HandbookDataStorage implements Serializable {
    private static volatile HandbookDataStorage instance;
    private List<Contact> contacts = new ArrayList();
    private List<Group> groups = new ArrayList();

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

    public List<Contact> getContacts() {
        return contacts;
    }

    public List<Group> getGroups() {
        return groups;
    }
}
