package ru.handbook.controller;

import ru.handbook.dao.ContactDAOImpl;

public class ContactControllerDAOImpl implements ControllerDAO {
    ContactDAOImpl contactDAO = new ContactDAOImpl();

    public void search() {
        contactDAO.search().getContactInfo();
    }

    public void create() {
        contactDAO.create();
    }

    public void update() {
        contactDAO.update();
    }

    public void delete() {
        contactDAO.delete();
    }

    public void addInGroup() {
        contactDAO.addInGroup();
    }

    public void removeFromGroup() {
        contactDAO.removeFromGroup();
    }

    public void check() {
        contactDAO.check();
    }
}
