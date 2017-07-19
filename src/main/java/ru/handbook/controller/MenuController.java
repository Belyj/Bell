package ru.handbook.controller;

import ru.handbook.core.SystemMessages;

/**
 * Created by operator1 on 14.07.2017.
 */
public class MenuController {
    SystemMessages messenger = new SystemMessages();

    public void searchContact() {
//        if (contactActions.search() != null) {
            new ContactActions().search().getContactInfo();
//        } else {
//            messenger.emptyList("Contact");
//        }
    }

    public void createContact() {
        new ContactActions().create();
    }

    public void updateContact() {
        new ContactActions().update();
    }

    public void deleteContact() {
        new ContactActions().delete();
    }

    public void addInGroup() {
        new ContactActions().addInGroup();
    }

    public void deleteFromGroup() {
        new ContactActions().deleteFromGroup();
    }

    public void checkContacts() {
            new ContactActions().check();
    }

    public void searchGroup() {

//        if (new GroupActions().search() != null) {
            new GroupActions().search().getGroupInfo();
//        } else {
//            messenger.emptyList("Group");
//        }
    }

    public void createGroup() {
        new GroupActions().create();
    }

    public void checkGroups() {
        new GroupActions().check();
    }

    public void deleteGroup() {
        new GroupActions().delete();
    }

    public void updateGroup() {
        new GroupActions().update();
    }
}
