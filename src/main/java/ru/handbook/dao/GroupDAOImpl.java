package ru.handbook.dao;

import ru.handbook.model.Group;
import ru.handbook.model.HandbookDataStorage;

import static ru.handbook.core.Main.scanner;

/**
 * Created by asus on 16.07.2017.
 */
public class GroupDAOImpl implements ObjectDAO<Group> {
    HandbookDataStorage dataSource = HandbookDataStorage.getInstance();
    public void create() {
        messenger.nameRequest("group");
        String groupName = scanner.nextLine();
        if (!groupName.equals("")) {
            int groupsLength = dataSource.getGroups().size();
            for (int i = 0; i < groupsLength; i++) {
                if (dataSource.getGroups().get(i).getGroupName().equals(groupName)) {
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
            if (dataSource.getGroups().get(i).getGroupName().equals(groupName)) {
                return dataSource.getGroups().get(i);
            }
        }
        messenger.nameNonexistent(groupName);
        return new Group("");
    }

    public void update() {
        messenger.nameRequest("group");
        String groupName = scanner.nextLine();
        int groupLength = dataSource.getGroups().size();
        for (int i = 0; i < groupLength; i++) {
            if (dataSource.getGroups().get(i).getGroupName().equals(groupName)) {
                messenger.newNameRequest("contact");
                String newGroupName = scanner.nextLine();
                dataSource.getGroups().get(i).setName(newGroupName);
                int contactsLength = dataSource.getContacts().size();
                for (int j = 0; j < contactsLength; j++) {
                    if (dataSource.getContacts().get(j).getContactGroups() != null) {
                        int contactGroupsLength = dataSource.getContacts().get(j).getContactGroups().size();
                        for (int k = 0; k < contactGroupsLength; k++) {
                            if (dataSource.getContacts().get(j).getContactGroups().get(k).equals(groupName)) {
                                dataSource.getContacts().get(j).getContactGroups().remove(k);
                                dataSource.getContacts().get(j).getContactGroups().add(newGroupName);
                                return;
                            }
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
        int groupLength = dataSource.getGroups().size();
        for (int i = 0; i < groupLength; i++) {
            if (dataSource.getGroups().get(i).getGroupName().equals(groupName)) {
                int groupContactsLength = dataSource.getGroups().get(i).getGroupContacts().size();
                for (int j = 0; j < groupContactsLength; j++) {
                    int contactGroupsLength = dataSource.getGroups().get(i).getGroupContacts().get(j).getContactGroups().size();
                    for (int k = 0; k < contactGroupsLength; k++) {
                        if (dataSource.getGroups().get(i).getGroupContacts().get(j).getContactGroups().get(k).equals(groupName)) {
                            dataSource.getGroups().get(i).getGroupContacts().get(j).getContactGroups().remove(k);
                            dataSource.getGroups().remove(i);
                            messenger.removeSuccess(groupName);
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
                System.out.println(dataSource.getGroups().get(i).getGroupName());
            }
        } else messenger.emptyList("Group list");
    }
}
