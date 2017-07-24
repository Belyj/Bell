package ru.handbook.dao;

import ru.handbook.model.Contact;
import ru.handbook.model.Group;
import ru.handbook.model.HandbookDataStorage;

import java.util.ArrayList;
import java.util.List;

import static ru.handbook.core.Main.scanner;

/**
 * Created by asus on 16.07.2017.
 */
public class GroupDAOImpl implements ObjectDAO<Group> {

    HandbookDataStorage dataSource = HandbookDataStorage.getInstance();

    public void create() {
        messenger.nameRequest("group");
        String groupName = scanner.nextLine();
        if (!groupName.isEmpty()) {
            int groupsLength = dataSource.getGroups().size();
            for (int i = 0; i < groupsLength; i++) {
                if (dataSource.getGroups().get(i).getName().equals(groupName)) {
                    messenger.nameIsBusy(groupName);
                    return;
                }
            }
            dataSource.getGroups().add(new Group(groupName));
            messenger.createSuccess(groupName);
        } else {
            messenger.emptyName(groupName);
        }
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

    public void update() {
        messenger.nameRequest("group");
        String groupName = scanner.nextLine();
        for (Group group : dataSource.getGroups()) {
            if (group.getName().equals(groupName)) {
                messenger.newNameRequest("group");
                String newGroupName = scanner.nextLine();
                dataSource.getGroupByName(groupName).setName(newGroupName);
                for (Contact contact : dataSource.getContacts()) {
                    for (int i = 0; i < contact.getContactGroups().size(); i++) {
                        if (contact.getContactGroups().get(i).equals(groupName)) {
                            contact.getContactGroups().remove(i);
                            contact.getContactGroups().add(newGroupName);
                            System.out.println("Group update to " + newGroupName);
                            return;
                        }
                    }
                }
            }
        }
        messenger.nameNonexistent(groupName);
    }

    public void delete() {
        messenger.nameRequest("group");
        String groupName = scanner.nextLine();
        for (Group group : dataSource.getGroups()) {
            if (group.getName().equals(groupName)) {
                dataSource.getGroups().remove(group);
                messenger.removeSuccess(groupName);
                for (Contact contact : dataSource.getContacts()) {
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
