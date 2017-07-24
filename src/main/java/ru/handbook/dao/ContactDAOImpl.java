package ru.handbook.dao;

import ru.handbook.model.Contact;
import ru.handbook.model.Group;
import ru.handbook.model.HandbookDataStorage;

import java.util.List;

import static ru.handbook.core.Main.scanner;

/**
 * Created by asus on 16.07.2017.
 */
public class ContactDAOImpl implements ObjectDAO<Contact> {

    HandbookDataStorage dataSource = HandbookDataStorage.getInstance();

    private boolean isContactNameExist(List<Contact> objects, String name) {
        for (Contact object : objects) {
            if (object.getName().equals(name)) {
                return true;
            }
        }
        return false;
    }

    private boolean isGroupNameExist(List<Group> objects, String name) {
        for (Group object : objects) {
            if (object.getName().equals(name)) {
                return true;
            }
        }
        return false;
    }

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

    public void update() {
        messenger.nameRequest("contact");
        String name = scanner.nextLine();
        if (isContactNameExist(dataSource.getContacts(), name)) {
            System.out.println("Would you update name? y/n");
            String yn = scanner.nextLine();
            String newName = "";
            if (yn.equals("y")) {
                messenger.newNameRequest("contact");
                newName = scanner.nextLine();
                if (!newName.isEmpty()) {
                    if (!isContactNameExist(dataSource.getContacts(), newName)) {
                        int groupsLength = dataSource.getGroups().size();
                        for (int i = 0; i < groupsLength; i++) {
                            int groupContactsLength = dataSource.getGroups().get(i).getGroupContacts().size();
                            for (int j = 0; j < groupContactsLength; j++) {
                                if (dataSource.getGroups().get(j) != null) {
                                    if ((dataSource.getGroups().get(j).getName().equals(name))) {
                                        dataSource.getGroupByName(dataSource.getGroups().get(j).getName()).getGroupContacts().remove(dataSource.getContactByName(name));
                                        dataSource.getContactByName(name).setName(newName);
                                        int groupContactLength = dataSource.getGroupByName(dataSource.getGroups().get(j).getName()).getGroupContacts().size();
                                        for (int k = 0; k < groupContactLength; k++) {
                                            if (dataSource.getGroupByName(dataSource.getGroups().get(j).getName()).getGroupContacts().get(j).getName().equals(name)) {
                                                dataSource.getGroupByName(dataSource.getGroups().get(j).getName()).getGroupContacts().get(j).setName(newName);
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

    public void delete() {
        messenger.nameRequest("contact");
        String contactName = scanner.nextLine();
        if (isContactNameExist(dataSource.getContacts(), contactName)) {
            dataSource.getContacts().remove(dataSource.getContactByName(contactName));
            System.out.println("Removing " + contactName + " complete");
            int groupLength = dataSource.getGroups().size();
            for (int i = 0; i < groupLength; i++) {
                if (isContactNameExist(dataSource.getGroups().get(i).getGroupContacts(), contactName)) {
                    int groupContactsLength = dataSource.getGroups().get(i).getGroupContacts().size();
                    for (int j = 0; j < groupContactsLength; j++) {
                        if (dataSource.getGroups().get(i).getGroupContacts().get(j).getName().equals(contactName)) {
                            dataSource.getGroups().get(i).getGroupContacts().remove(j);
                        }
                    }
                    return;
                }
            }
        }
        messenger.nameNonexistent(contactName);
    }

    public void addInGroup() {

        messenger.nameRequest("contact");
        String contactName = scanner.nextLine();
        if (isContactNameExist(dataSource.getContacts(), contactName)) {
            messenger.nameRequest("group");
            String groupName = scanner.nextLine();
            if (isGroupNameExist(dataSource.getGroups(), groupName)) {
                dataSource.getContactByName(contactName).setContactGroups(groupName);
                dataSource.getGroupByName(groupName).setGroupContact(dataSource.getContactByName(contactName));
                System.out.println("Contact " + contactName + " was added to " + groupName);
                return;
            }
            messenger.nameNonexistent(groupName);
            return;
        }
        messenger.nameNonexistent(contactName);
    }

    public void removeFromGroup() {

        messenger.nameRequest("contact");
        String contactName = scanner.nextLine();
        if (isContactNameExist(dataSource.getContacts(), contactName)) {
            messenger.nameRequest("group");
            String groupName = scanner.nextLine();
            if (isGroupNameExist(dataSource.getGroups(), groupName)) {
                dataSource.getContactByName(contactName).getContactGroups().remove(groupName);
                dataSource.getGroupByName(groupName).getGroupContacts().remove(dataSource.getContactByName(contactName));
                System.out.println("Contact " + contactName + " was removed from " + groupName);
                return;
            }
            messenger.nameNonexistent(groupName);
            return;
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
