package ru.handbook.controller;

import ru.handbook.dao.ContactDAOImpl;
import ru.handbook.dao.GroupDAOImpl;

public class MenuControllerDAOImpl implements GroupControllerDAO, ContactControllerDAO {

    ContactDAOImpl contactDAO = new ContactDAOImpl();
    GroupDAOImpl groupDAO = new GroupDAOImpl();

    public void searchContact() {
        contactDAO.search().getContactInfo();
    }

    public void createContact() {
        contactDAO.create();
    }

    public void updateContact() {
        try {
            contactDAO.update();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
    }

    public void deleteContact() {
        try {
            contactDAO.delete();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
    }

    public void addInGroup() {
        try {
            contactDAO.addInGroup();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
    }

    public void removeFromGroup() {
        try {
            contactDAO.removeFromGroup();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
    }

    public void checkContacts() {
        contactDAO.check();
    }

    public void searchGroup() {
        groupDAO.search().getGroupInfo();
    }

    public void createGroup() {
        groupDAO.create();
    }

    public void updateGroup() {
        try {
            groupDAO.update();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
    }

    public void deleteGroup() {
        try {
            groupDAO.delete();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
    }
    public void checkGroups() {
        groupDAO.check();
    }
}
