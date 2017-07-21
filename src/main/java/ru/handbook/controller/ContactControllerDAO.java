package ru.handbook.controller;

public interface ContactControllerDAO {

    void searchContact();

    void createContact();

    void updateContact();

    void deleteContact();

    void addInGroup();

    void removeFromGroup();

    void checkContacts();
}
