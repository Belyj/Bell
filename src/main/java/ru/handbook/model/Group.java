package ru.handbook.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by operator1 on 13.07.2017.
 */
public class Group implements Serializable {

    private String groupName;

    private List<Contact> groupContacts;

    public Group(String name) {
        this.groupName = name;
    }

    public List<Contact> getGroupContacts() {
        return groupContacts;
    }

    public void setName(String name) {
        this.groupName = name;
    }

    public String getName() {
        return groupName;
    }

    public void setGroupContact(Contact contact) {
        if (groupContacts != null) {
            groupContacts.add(contact);
        }
        groupContacts = new ArrayList<Contact>();
        groupContacts.add(contact);
    }

    public void getGroupInfo() {
        if (groupContacts != null) {
            int length = groupContacts.size();
            for (int i = 0; i < length; i++) {
                System.out.println(groupContacts.get(i).getName());
                return;
            }
        }
        System.out.println("Group has not any contacts");
    }

    public void removeContact(String name) {
        groupContacts.remove(name);
    }
}
