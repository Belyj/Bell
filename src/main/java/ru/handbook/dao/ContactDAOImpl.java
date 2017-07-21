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

    public void create() {
        messenger.nameRequest("contact");
        String name = scanner.nextLine();
        if (!name.isEmpty()) {
            for (Contact contact : dataSource.getContacts()) {
                if (contact.getContactName().equals(name)) {
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
            if (contact.getContactName().equals(name)) {
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
            if (contact.getContactName().equals(name)) {
                System.out.println("Would you update name? y/n");
                String yn = scanner.nextLine();
                String newName = "";
                if (yn.equals("y")) {
                    messenger.newNameRequest("contact");
                    newName = scanner.nextLine();
                    if (!newName.isEmpty()) {
                        for (Contact contact1 : dataSource.getContacts()) {
                            if (!contact1.getContactName().equals(newName)) {
                                contact1.setContactName(newName);
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
                    if (contact1.getContactName().equals(newName)) {
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
            if (contact.getContactName().equals(contactName)) {
                for (Group group : dataSource.getGroups()) {
                    for (Contact groupContact : group.getGroupContacts()) {
                        if (groupContact.getContactName().equals(contactName)) {
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

    public void addInGroup() {
        List<Contact> intermediateContacts = dataSource.getContacts();
        List<Group> intermediateGroups = dataSource.getGroups();
        messenger.nameRequest("contact");
        String contactName = scanner.nextLine();
        for (Contact contact : intermediateContacts) {
            if (contact.getContactName().equals(contactName)) {
                messenger.nameRequest("group");
                String groupName = scanner.nextLine();
                for (Group group : intermediateGroups) {
                    if (group.getGroupName().equals(groupName)) {
                        dataSource.getContacts().add(contact);
                        //dataSource.getGroups().setGroupContact(contact);
                    }
                }
            }
        }
        messenger.nameNonexistent(contactName);
        return;
    }

    public void removeFromGroup() {
        messenger.nameRequest("contact");
        String contactName = scanner.nextLine();
        for (Contact contact : dataSource.getContacts()) {
            if (contact.getContactName().equals(contactName)) {
                messenger.nameRequest("group");
                String groupName = scanner.nextLine();
                for (Group group : dataSource.getGroups()) {
                    if (group.getGroupName().equals(groupName)) {
                        contact.getContactGroups().remove(groupName);
                        group.removeContact(contactName);
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
                System.out.println(dataSource.getContacts().get(i).getContactName());
            }
        } else messenger.emptyList("Contact list");
    }
}
