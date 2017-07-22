package ru.handbook.dao;

import ru.handbook.model.Contact;
import ru.handbook.model.Group;
import ru.handbook.model.HandbookDataStorage;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import static ru.handbook.core.Main.scanner;

/**
 * Created by asus on 16.07.2017.
 */
public class ContactDAOImpl implements ObjectDAO<Contact> {

    HandbookDataStorage dataSource = HandbookDataStorage.getInstance();

    private void searchingByName(List<Serializable> searcheable) {
        for (Serializable serializable : searcheable) {

        }
    }

    public void create() {
        messenger.nameRequest("contact");
        String name = scanner.nextLine();
        if (!name.isEmpty()) {
            for (Contact contact : dataSource.getContacts()) {
                if (contact.getName().equals(name)) {
                    messenger.nameIsBusy(name);
                    return;
                }
            }
            dataSource.getContacts().add(new Contact(name));
            dataSource.notifyObservers();
            messenger.createSuccess(name);
        } else messenger.emptyName(name);
    }

    public Contact search() {
        messenger.nameRequest("contact");
        String name = scanner.nextLine();
        for (Contact contact : dataSource.getContacts()) {
            if (contact.getName().equals(name)) {
                return contact;
            }
        }
        messenger.nameNonexistent(name);
        return new Contact("");
    }

    public void update() {
        messenger.nameRequest("contact");
        String name = scanner.nextLine();
        for (Contact contact : dataSource.getContacts()) {
            if (contact.getName().equals(name)) {
                System.out.println("Would you update name? y/n");
                String yn = scanner.nextLine();
                String newName = "";
                if (yn.equals("y")) {
                    messenger.newNameRequest("contact");
                    newName = scanner.nextLine();
                    if (!newName.isEmpty()) {
                        for (Contact contact1 : dataSource.getContacts()) {
                            if (!contact1.getName().equals(newName)) {
                                contact1.setName(newName);
                                dataSource.notifyObservers();
                                return;
                            } else {
                                messenger.nameIsBusy(newName);
                                return;
                            }
                        }
                    } else {
                        messenger.emptyName(newName);
                        return;
                    }
                }
                messenger.newDataRequest("telephone");
                String quest = scanner.nextLine();
                contact.setTelephone(quest);
                messenger.newDataRequest("skype");
                quest = scanner.nextLine();
                contact.setSkype(quest);
                messenger.newDataRequest("mail");
                quest = scanner.nextLine();
                contact.setMail(quest);
                for (Contact contact1 : dataSource.getContacts()) {
                    if (contact1.getName().equals(newName)) {
                        contact1.getContactInfo();
                        dataSource.notifyObservers();
                        return;
                    }
                }
            }
        }
        messenger.nameNonexistent(name);
    }

    public void delete() {
        messenger.nameRequest("contact");
        String contactName = scanner.nextLine();
        for (Contact contact : dataSource.getContacts()) {
            if (contact.getName().equals(contactName)) {
                for (Group group : dataSource.getGroups()) {
                    for (Contact groupContact : group.getGroupContacts()) {
                        if (groupContact.getName().equals(contactName)) {
                            dataSource.getContacts().remove(contact);
                            group.getGroupContacts().remove(groupContact);
                            dataSource.notifyObservers();
                            return;
                        }
                    }
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
        for (Contact contact : intermediateContacts) {
            if (contact.getName().equals(contactName)) {
                messenger.nameRequest("group");
                String groupName = scanner.nextLine();
                for (Group group : intermediateGroups) {
                    if (group.getGroupName().equals(groupName)) {
                        dataSource.getContactByName(contactName).setContactGroups(groupName);
                        dataSource.getGroupByName(groupName).setGroupContact(contact);
                    }
                }
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
        for (Contact contact : intermediateContacts) {
            if (contact.getName().equals(contactName)) {
                messenger.nameRequest("group");
                String groupName = scanner.nextLine();
                for (Group group : intermediateGroups) {
                    if (group.getGroupName().equals(groupName)) {
                        dataSource.getContactByName(contactName).getContactGroups().remove(groupName);
                        dataSource.getGroupByName(groupName).removeContact(contactName);
                    }
                }
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
