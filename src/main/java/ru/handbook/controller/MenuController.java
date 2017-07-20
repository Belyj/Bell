package ru.handbook.controller;

import ru.handbook.core.SystemMessages;
import ru.handbook.dao.ContactDAOImpl;
import ru.handbook.dao.GroupDAOImpl;

/**
 * Created by operator1 on 14.07.2017.
 */
public class MenuController {
    SystemMessages messenger = new SystemMessages();

    public void searchContact() {
            new ContactDAOImpl().search().getContactInfo();
    }

    public void createContact() {
        new ContactDAOImpl().create();
    }

    public void updateContact() {
        new ContactDAOImpl().update();
    }

    public void deleteContact() {
        new ContactDAOImpl().delete();
    }

    public void addInGroup() {
        new ContactDAOImpl().addInGroup();
    }

    public void deleteFromGroup() {
        new ContactDAOImpl().removeFromGroup();
    }

    public void checkContacts() {
            new ContactDAOImpl().check();
    }

    public void searchGroup() {
            new GroupDAOImpl().search().getGroupInfo();
    }

    public void createGroup() {
        new GroupDAOImpl().create();
    }

    public void checkGroups() {
        new GroupDAOImpl().check();
    }

    public void deleteGroup() {
        new GroupDAOImpl().delete();
    }

    public void updateGroup() {
        new GroupDAOImpl().update();
    }
}
