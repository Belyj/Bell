package ru.handbook.dao;

import ru.handbook.model.Contact;
import ru.handbook.model.HandbookDataStorage;
import static ru.handbook.core.Main.scanner;

/**
 * Created by asus on 16.07.2017.
 */
public class ContactDAOImpl implements ObjectDAO<Contact> {
    HandbookDataStorage dataSource = HandbookDataStorage.getInstance();
    public void create() {
        messenger.nameRequest("contact");
        String name = scanner.nextLine();
        if (!name.equals("")) {
            int contactsLength = dataSource.getContacts().size();
            for (int i = 0; i < contactsLength; i++) {
                if (dataSource.getContacts().get(i).getContactName().equals(name)) {
                    messenger.nameIsBusy(name);
                    return;
                }
            }
            dataSource.getContacts().add(new Contact(name));
            messenger.createSuccess(name);
        } else messenger.emptyName(name);
    }

    public Contact search() {
        messenger.nameRequest("contact");
        String name = scanner.nextLine();
        int length = dataSource.getContacts().size();
        for (int i = 0; i < length; i++) {
            if (dataSource.getContacts().get(i).getContactName().equals(name)) {
                return dataSource.getInstance().getContacts().get(i);
            }
        }
        messenger.nameNonexistent(name);
        return new Contact("");
    }

    public void update() {
        messenger.nameRequest("contact");
        String name = scanner.nextLine();
        int length = dataSource.getContacts().size();
        for (int i = 0; i < length; i++) {
            if (dataSource.getContacts().get(i).getContactName().equals(name)) {
                System.out.println("Would you update name? y/n");
                String yn = scanner.nextLine();
                String newName = "";
                if (yn.equals("y")) {
                    messenger.newNameRequest("contact");
                    newName = scanner.nextLine();
                    if (!newName.equals("")) {
                        for (int j = 0; j < length; j++) {
                            if (!dataSource.getContacts().get(j).getContactName().equals(newName)) {
                                dataSource.getContacts().get(j).setContactName(newName);
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
                dataSource.getContacts().get(i).setTelephone(quest);
                messenger.newDataRequest("skype");
                quest = scanner.nextLine();
                dataSource.getContacts().get(i).setSkype(quest);
                messenger.newDataRequest("mail");
                quest = scanner.nextLine();
                dataSource.getContacts().get(i).setMail(quest);
                for (int j = 0; j < length; j++) {
                    if (dataSource.getContacts().get(j).getContactName().equals(newName)) {
                        dataSource.getContacts().get(j).getContactInfo();
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
        int lengthContacts = dataSource.getContacts().size();
        for (int i = 0; i < lengthContacts; i++) {
            if (dataSource.getContacts().get(i).getContactName().equals(contactName)) {
                int groupsLength = dataSource.getGroups().size();
                for (int j = 0; j < groupsLength; j++) {
                    int groupContactsLength = dataSource.getGroups().get(j).getGroupContacts().size();
                    for (int k = 0; k < groupContactsLength; k++) {
                        if (dataSource.getGroups().get(j).getGroupContacts().get(k).getContactName().equals(contactName)) {
                            dataSource.getGroups().get(j).getGroupContacts().remove(k);
                            dataSource.getContacts().remove(i);
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
        int contactsLength = dataSource.getContacts().size();
        for (int i = 0; i < contactsLength; i++) {
            if (dataSource.getContacts().get(i).getContactName().equals(contactName)) {
                messenger.nameRequest("group");
                String groupName = scanner.nextLine();
                int groupsLenght = dataSource.getGroups().size();
                for (int j = 0; j < groupsLenght; j++) {
                    if (dataSource.getGroups().get(j).getGroupName().equals(groupName)) {
                        dataSource.getGroups().get(j).setGroupContact(dataSource.getContacts().get(i));
                        dataSource.getContacts().get(i).setContactGroups(groupName);
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
        int contactsLength = dataSource.getContacts().size();
        for (int i = 0; i < contactsLength; i++) {
            if (dataSource.getContacts().get(i).getContactName().equals(contactName)) {
                messenger.nameRequest("group");
                String groupName = scanner.nextLine();
                int groupsLength = dataSource.getGroups().size();
                for (int j = 0; j < groupsLength; j++) {
                    if (dataSource.getGroups().get(j).getGroupName().equals(groupName)) {
                        int contactGroupsLength = dataSource.getContacts().get(i).getContactGroups().size();
                        for (int k = 0; k < contactGroupsLength; k++) {
                            if (dataSource.getContacts().get(i).getContactGroups().get(k).equals(groupName)) {
                                dataSource.getContacts().get(i).getContactGroups().remove(k);
                                dataSource.getGroups().get(j).removeContact(contactName);
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
        if (!dataSource.getContacts().isEmpty()) {
            int contactsLength = dataSource.getContacts().size();
            for (int i = 0; i < contactsLength; i++) {
                System.out.println(dataSource.getContacts().get(i).getContactName());
            }
        } else messenger.emptyList("Contact list");
    }
}
