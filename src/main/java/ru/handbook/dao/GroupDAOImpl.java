package ru.handbook.dao;

import ru.handbook.model.product.Contact;
import ru.handbook.model.factory.GroupFactory;
import ru.handbook.model.product.Group;
import ru.handbook.model.HandbookDataStorage;
import ru.handbook.model.product.HandbookObject;

import java.util.ArrayList;
import java.util.List;

import static ru.handbook.core.Main.scanner;

/**
 * Created by asus on 16.07.2017.
 */
public class GroupDAOImpl implements ObjectDAO<Group> {

    HandbookDataStorage dataSource = HandbookDataStorage.getInstance();

    GroupFactory groupFactory = new GroupFactory();

    public boolean isGroupNameExist(List<Group> objects, String name) {
        for (HandbookObject object : objects) {
            if (object.getName().equals(name)) {
                return true;
            }
        }
        return false;
    }

    public void create() {
        messenger.nameRequest("group");
        String name = scanner.nextLine();
        dataSource.getGroups().add(groupFactory.create(name));
        messenger.createSuccess(name);
    }

    public Group search() {
        messenger.nameRequest("group");
        String groupName = scanner.nextLine();
        System.out.println(groupName + ":\n");
        int length = dataSource.getGroups().size();
        for (int i = 0; i < length; i++) {
            if (dataSource.getGroups().get(i).getName().equals(groupName)) {
                return dataSource.getGroups().get(i);
            }
        }
        messenger.nameNonexistent(groupName);
        return new Group("");
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

    public void update() throws CloneNotSupportedException {
        List<Contact> intermediateContacts = getCloneContact(dataSource.getContacts());
        List<Group> intermediateGroups = getCloneGroup(dataSource.getGroups());
        messenger.nameRequest("group");
        String groupName = scanner.nextLine();
        for (Group group : intermediateGroups) {
            if (group.getName().equals(groupName)) {
                messenger.newNameRequest("group");
                String newGroupName = scanner.nextLine();
                dataSource.getGroupByName(groupName).setName(newGroupName);
                for (Contact contact : intermediateContacts) {
                    for (int i = 0; i < contact.getContactGroups().size(); i++) {
                        if (contact.getContactGroups().get(i).equals(groupName)) {
                            contact.getContactGroups().remove(i);
                            contact.getContactGroups().add(newGroupName);
                            return;
                        }
                    }
                }
            }
        }
        messenger.nameNonexistent(groupName);
    }

    public void delete() throws CloneNotSupportedException {
        List<Contact> intermediateContacts = getCloneContact(dataSource.getContacts());
        List<Group> intermediateGroups = getCloneGroup(dataSource.getGroups());
        messenger.nameRequest("group");
        String groupName = scanner.nextLine();
        for (Group group : intermediateGroups) {
            if (group.getName().equals(groupName)) {
                dataSource.getGroups().remove(group);
                messenger.removeSuccess(groupName);
                for (Contact contact : intermediateContacts) {
                    for (int i = 0; i < contact.getContactGroups().size(); i++) {
                        if (contact.getContactGroups().get(i).equals(groupName)) {
                            contact.getContactGroups().remove(i);
                            return;
                        }
                    }
                }
            }
        }
        messenger.nameNonexistent(groupName);
    }

    public void check() {
        int groupsLength = dataSource.getGroups().size();
        if (!dataSource.getGroups().isEmpty()) {
            for (int i = 0; i < groupsLength; i++) {
                System.out.println(dataSource.getGroups().get(i).getName());
            }
        } else messenger.emptyList("Group list");
    }
}
