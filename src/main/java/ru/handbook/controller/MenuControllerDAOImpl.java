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

        contactDAO.update();
    }

    public void deleteContact() {

        contactDAO.delete();
    }

    public void addInGroup() {

        contactDAO.addInGroup();
    }

    public void removeFromGroup() {

        contactDAO.removeFromGroup();
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

        groupDAO.update();
    }

    public void deleteGroup() {

        groupDAO.delete();
    }
    public void checkGroups() {

        groupDAO.check();
    }
}
