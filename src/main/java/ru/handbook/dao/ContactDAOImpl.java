package ru.handbook.controller;

import ru.handbook.model.Contact;

import static ru.handbook.core.Main.contacts;
import static ru.handbook.core.Main.groups;
import static ru.handbook.core.Main.scanner;

/**
 * Created by asus on 16.07.2017.
 */
public class ContactDAOimpl implements ObjectDAO<Contact> {
    public void create() {
        messenger.nameRequest("contact");
        String name = scanner.nextLine();
        if (!name.equals("")) {
            int contactsLength = contacts.size();
            for (int i = 0; i < contactsLength; i++) {
                if (contacts.get(i).getContactName().equals(name)) {
                    messenger.nameIsBusy(name);
                    return;
                }
            }
            contacts.add(new Contact(name));
            messenger.createSuccess(name);
        } else messenger.emptyName(name);
    }

    public Contact search() {
        messenger.nameRequest("contact");
        String name = scanner.nextLine();
        int length = contacts.size();
        for (int i = 0; i < length; i++) {
            if (contacts.get(i).getContactName().equals(name)) {
                return contacts.get(i);
            }
        }
        messenger.nameNonexistent(name);
        return new Contact("");
    }

    public void update() {
        messenger.nameRequest("contact");
        String name = scanner.nextLine();
        int length = contacts.size();
        for (int i = 0; i < length; i++) {
            if (contacts.get(i).getContactName().equals(name)) {
                System.out.println("Would you update name? y/n");
                String yn = scanner.nextLine();
                String newName = "";
                if (yn.equals("y")) {
                    messenger.newNameRequest("contact");
                    newName = scanner.nextLine();
                    if (!newName.equals("")) {
                        for (int j = 0; j < length; j++) {
                            if (!contacts.get(j).getContactName().equals(newName)) {
                                contacts.get(j).setContactName(newName);
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
                contacts.get(i).setTelephone(quest);
                messenger.newDataRequest("skype");
                quest = scanner.nextLine();
                contacts.get(i).setSkype(quest);
                messenger.newDataRequest("mail");
                quest = scanner.nextLine();
                contacts.get(i).setMail(quest);
                for (int j = 0; j < length; j++) {
                    if (contacts.get(j).getContactName().equals(newName)) {
                        contacts.get(j).getContactInfo();
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
        int lengthContacts = contacts.size();
        for (int i = 0; i < lengthContacts; i++) {
            if (contacts.get(i).getContactName().equals(contactName)) {
                int groupsLength = groups.size();
                for (int j = 0; j < groupsLength; j++) {
                    int groupContactsLength = groups.get(j).getGroupContacts().size();
                    for (int k = 0; k < groupContactsLength; k++) {
                        if (groups.get(j).getGroupContacts().get(k).getContactName().equals(contactName)) {
                            groups.get(j).getGroupContacts().remove(k);
                            contacts.remove(i);
                            messenger.removeSuccess(contactName);
                            return;
                        }
                    }
                }
            }
        }
        messenger.nameNonexistent(contactName);
    }

    public void addInGroup() {
        messenger.nameRequest("contact");
        String contactName = scanner.nextLine();
        int contactsLength = contacts.size();
        for (int i = 0; i < contactsLength; i++) {
            if (contacts.get(i).getContactName().equals(contactName)) {
                messenger.nameRequest("group");
                String groupName = scanner.nextLine();
                int groupsLenght = groups.size();
                for (int j = 0; j < groupsLenght; j++) {
                    if (groups.get(j).getGroupName().equals(groupName)) {
                        groups.get(j).setGroupContact(contacts.get(i));
                        contacts.get(i).setContactGroups(groupName);
                        messenger.addGroupSuccess(contactName, groupName);
                        return;
                    }
                }
                messenger.nameNonexistent(groupName);
                return;
            }
        }
        messenger.nameNonexistent(contactName);
        return;
    }

    public void removeFromGroup() {
        messenger.nameRequest("contact");
        String contactName = scanner.nextLine();
        int contactsLength = contacts.size();
        for (int i = 0; i < contactsLength; i++) {
            if (contacts.get(i).getContactName().equals(contactName)) {
                messenger.nameRequest("group");
                String groupName = scanner.nextLine();
                int groupsLength = groups.size();
                for (int j = 0; j < groupsLength; j++) {
                    if (groups.get(j).getGroupName().equals(groupName)) {
                        int contactGroupsLength = contacts.get(i).getContactGroups().size();
                        for (int k = 0; k < contactGroupsLength; k++) {
                            if (contacts.get(i).getContactGroups().get(k).equals(groupName)) {
                                contacts.get(i).getContactGroups().remove(k);
                                groups.get(j).removeContact(contactName);
                                messenger.removeGroupSuccess(contactName, groupName);
                                return;
                            }
                        }
                    }
                }
                messenger.nameNonexistent(groupName);
                return;
            }
        }
        messenger.nameNonexistent(contactName);
    }
    
    public void check() {
        if (!contacts.isEmpty()) {
            int contactsLength = contacts.size();
            for (int i = 0; i < contactsLength; i++) {
                System.out.println(contacts.get(i).getContactName());
            }
        } else messenger.emptyList("Contact list");
    }
}
