package ru.handbook.controller;

import ru.handbook.core.SystemMessages;
import ru.handbook.dao.ContactDAOImpl;
import ru.handbook.dao.GroupDAOImpl;

/**
 * Created by operator1 on 14.07.2017.
 */
public class MenuController {
    ContactControllerDAOImpl contactControllerDAO = new ContactControllerDAOImpl();
    GroupControllerDAOImpl groupControllerDAO = new GroupControllerDAOImpl();
    SystemMessages messenger = new SystemMessages();

    public void searchContact() {
        contactControllerDAO.search();
    }

    public void createContact() {
        contactControllerDAO.create();
    }

    public void updateContact() {
        contactControllerDAO.update();
    }

    public void deleteContact() {
        contactControllerDAO.delete();
    }

    public void addInGroup() {
        contactControllerDAO.addInGroup();
    }

    public void removeFromGroup() {
        contactControllerDAO.removeFromGroup();
    }

    public void checkContacts() {
        contactControllerDAO.check();
    }

    public void searchGroup() {
        groupControllerDAO.search();
    }

    public void createGroup() {
        groupControllerDAO.create();
    }

    public void checkGroups() {
        groupControllerDAO.check();
    }

    public void deleteGroup() {
        groupControllerDAO.delete();
    }

    public void updateGroup() {
        groupControllerDAO.update();
    }
}
