package ru.handbook.model;

import ru.handbook.core.contactview.Observer;
import ru.handbook.serialization.Deserializer;

import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by operator1 on 14.07.2017.
 */
public class HandbookDataStorage implements Observable {

    private static volatile HandbookDataStorage instance;
    private List<Contact> contacts = new ArrayList();
    private List<Group> groups = new ArrayList();
    private List<Observer> observers = new ArrayList();
    static Deserializer deserializer = new Deserializer();

    private HandbookDataStorage() {
    }

    public Group getGroupByName(String name) {
        for (int i = 0; i < groups.size(); i++) {
            if (groups.get(i).getGroupName().equals(name)) {
                return groups.get(i);
            }
        }
        return null;
    }

    public Contact getContactByName(String name) {
        for (int i = 0; i < groups.size(); i++) {
            if (contacts.get(i).getName().equals(name)) {
                return contacts.get(i);
            }
        }
        return null;
    }

    public static HandbookDataStorage getInstance() {
        if (instance == null) {
            synchronized (HandbookDataStorage.class) {
                if (instance == null) {
                    instance = new HandbookDataStorage();
                    if (new File("temp.out").exists()) {
                        try {
                            instance = (HandbookDataStorage) deserializer.createOIS().readObject();
                        } catch (IOException e) {
                            e.printStackTrace();
                        } catch (ClassNotFoundException e) {
                            e.printStackTrace();
                        }
                    }
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

    public void addObserver(Observer observer) {
        observers.add(observer);
    }

    public void removeObserver(Observer observer) {
        observers.remove(observer);
    }

    public void notifyObservers() {
        for (Observer observer : observers) {
            observer.handleEvent(contacts);
        }
    }
}
