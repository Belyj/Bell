package ru.handbook.controller;

import ru.handbook.core.SystemMessages;

/**
 * Created by operator1 on 14.07.2017.
 */
public class MenuController {
    SystemMessages messenger = new SystemMessages();

    public void searchContact() {
            new ContactRealization().search().getContactInfo();
    }

    public void createContact() {
        new ContactRealization().create();
    }

    public void updateContact() {
        new ContactRealization().update();
    }

    public void deleteContact() {
        new ContactRealization().delete();
    }

    public void addInGroup() {
        new ContactRealization().addInGroup();
    }

    public void deleteFromGroup() {
        new ContactRealization().removeFromGroup();
    }

    public void checkContacts() {
            new ContactRealization().check();
    }

    public void searchGroup() {
            new GroupRealization().search().getGroupInfo();
    }

    public void createGroup() {
        new GroupRealization().create();
    }

    public void checkGroups() {
        new GroupRealization().check();
    }

    public void deleteGroup() {
        new GroupRealization().delete();
    }

    public void updateGroup() {
        new GroupRealization().update();
    }
}
