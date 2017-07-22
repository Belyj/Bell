package ru.handbook.dao;

import ru.handbook.model.Contact;
import ru.handbook.model.Group;
import ru.handbook.model.HandbookDataStorage;
import ru.handbook.model.HandbookObject;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import static ru.handbook.core.Main.scanner;

/**
 * Created by asus on 16.07.2017.
 */
public class ContactDAOImpl implements ObjectDAO<Contact> {

    HandbookDataStorage dataSource = HandbookDataStorage.getInstance();

    private boolean isContactNameExist(List<Contact> objects, String name) {
        for (HandbookObject object : objects) {
            if (object.getName().equals(name)) {
                return true;
            }
        }
        return false;
    }

    private boolean isGroupNameExist(List<Group> objects, String name) {
        for (HandbookObject object : objects) {
            if (object.getName().equals(name)) {
                return true;
            }
        }
        return false;
    }

    ////////////////////////////////////

    public void create() {
        messenger.nameRequest("contact");
        String name = scanner.nextLine();
        if (!name.isEmpty()) {
            if (isContactNameExist(dataSource.getContacts(), name)) {
                messenger.nameIsBusy(name);
                return;
            }
            dataSource.getContacts().add(new Contact(name));
            messenger.createSuccess(name);
        } else messenger.emptyName(name);
    }

    public Contact search() {
        messenger.nameRequest("contact");
        String name = scanner.nextLine();
        if (isContactNameExist(dataSource.getContacts(), name)) {
            return dataSource.getContactByName(name);
        }
        messenger.nameNonexistent(name);
        return new Contact("");
    }

    public void update() throws CloneNotSupportedException {
        List<Contact> intermediateContacts = getCloneContact(dataSource.getContacts());
        List<Contact> intermediateContacts1 = getCloneContact(dataSource.getContacts());
        List<Group> intermediateGroups = getCloneGroup(dataSource.getGroups());
        messenger.nameRequest("contact");
        String name = scanner.nextLine();
        if (isContactNameExist(intermediateContacts, name)) {
            System.out.println("Would you update name? y/n");
            String yn = scanner.nextLine();
            String newName = "";
            if (yn.equals("y")) {
                messenger.newNameRequest("contact");
                newName = scanner.nextLine();
                if (!newName.isEmpty()) {
                    if (!isContactNameExist(intermediateContacts1, newName)) {
                        for (Group group : intermediateGroups) {
                            for (int i = 0; i < group.getGroupContacts().size(); i++) {
                                if (group != null) {
                                    if (group.getGroupContacts().get(i).getName().equals(name)) {
                                        dataSource.getGroupByName(group.getName()).getGroupContacts().remove(dataSource.getContactByName(name));
                                        dataSource.getContactByName(name).setName(newName);
                                        for (int j = 0; j < dataSource.getGroupByName(group.getName()).getGroupContacts().size(); j++) {
                                            if (dataSource.getGroupByName(group.getName()).getGroupContacts().get(j).getName().equals(name)) {
                                                dataSource.getGroupByName(group.getName()).getGroupContacts().get(j).setName(newName);
                                            }
                                        }
                                        System.out.println("Changes was applyed");
                                    }
                                }
                            }
                        }
                        dataSource.getContactByName(name).setName(newName);
                    } else {
                        messenger.nameIsBusy(newName);
                        return;
                    }
                } else {
                    messenger.emptyName(newName);
                    return;
                }
            }
            messenger.newDataRequest("telephone");
            String quest = scanner.nextLine();
            dataSource.getContactByName(newName).setTelephone(quest);
            messenger.newDataRequest("skype");
            quest = scanner.nextLine();
            dataSource.getContactByName(newName).setSkype(quest);
            messenger.newDataRequest("mail");
            quest = scanner.nextLine();
            dataSource.getContactByName(newName).setMail(quest);
            for (Contact contact1 : dataSource.getContacts()) {
                if (contact1.getName().equals(newName)) {
                    contact1.getContactInfo();
                    return;
                }
            }
        }
        messenger.nameNonexistent(name);
    }

    public void delete() throws CloneNotSupportedException {
        List<Contact> intermediateContacts = getCloneContact(dataSource.getContacts());
        List<Group> intermediateGroups = getCloneGroup(dataSource.getGroups());
        messenger.nameRequest("contact");
        String contactName = scanner.nextLine();
        if (isContactNameExist(intermediateContacts, contactName)) {
            dataSource.getContacts().remove(contactName);
            for (Group group : intermediateGroups) {
                if (isContactNameExist(group.getGroupContacts(), contactName)) {
                    dataSource.getGroupByName(group.getName()).getGroupContacts().remove(group.getGroupContacts().remove(contactName));
                    return;
                }
            }
        }
        messenger.nameNonexistent(contactName);
    }

    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public List<Contact> getCloneContact(List<Contact> incomingContacts) throws CloneNotSupportedException {
        List<Contact> contacts = new ArrayList();
        for (int i = 0; i < incomingContacts.size(); i++) {
            Contact contact;
            contact = incomingContacts.get(i).clone();
            contacts.add(contact);
        }
        return contacts;
    }

    public List<Group> getCloneGroup(List<Group> incomingGroup) throws CloneNotSupportedException {
        List<Group> groups = new ArrayList();
        for (int i = 0; i < incomingGroup.size(); i++) {
            Group group;
            group = dataSource.getGroups().get(i).clone();
            groups.add(group);
        }
        return groups;
    }

    //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public void addInGroup() throws CloneNotSupportedException {
        List<Contact> intermediateContacts = getCloneContact(dataSource.getContacts());
        List<Group> intermediateGroups = getCloneGroup(dataSource.getGroups());
        messenger.nameRequest("contact");
        String contactName = scanner.nextLine();
        if (isContactNameExist(intermediateContacts, contactName)) {
            messenger.nameRequest("group");
            String groupName = scanner.nextLine();
            if (isGroupNameExist(intermediateGroups, groupName)) {
                dataSource.getContactByName(contactName).setContactGroups(groupName);
                dataSource.getGroupByName(groupName).setGroupContact(dataSource.getContactByName(contactName));
            }
        }
        messenger.nameNonexistent(contactName);
        return;
    }

    public void removeFromGroup() throws CloneNotSupportedException {
        List<Contact> intermediateContacts = getCloneContact(dataSource.getContacts());
        List<Group> intermediateGroups = getCloneGroup(dataSource.getGroups());
        messenger.nameRequest("contact");
        String contactName = scanner.nextLine();
        if (isContactNameExist(intermediateContacts, contactName)) {
            messenger.nameRequest("group");
            String groupName = scanner.nextLine();
            if (isGroupNameExist(intermediateGroups, groupName)) {
                dataSource.getContactByName(contactName).getContactGroups().remove(groupName);
                dataSource.getGroupByName(groupName).removeContact(contactName);
            }
        }
        messenger.nameNonexistent(contactName);
    }

    public void check() {
        if (!dataSource.getContacts().isEmpty()) {
            int contactsLength = dataSource.getContacts().size();
            for (int i = 0; i < contactsLength; i++) {
                System.out.println(dataSource.getContacts().get(i).getName());
            }
        } else messenger.emptyList("Contact list");
    }
}
