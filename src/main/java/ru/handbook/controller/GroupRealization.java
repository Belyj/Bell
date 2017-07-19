package ru.handbook.controller;

import ru.handbook.model.Group;

import static ru.handbook.core.Main.contacts;
import static ru.handbook.core.Main.groups;
import static ru.handbook.core.Main.scanner;

/**
 * Created by asus on 16.07.2017.
 */
public class GroupRealization implements GroupDAO {
    @Override
    public void create() {
        messenger.nameRequest("group");
        String groupName = scanner.nextLine();
        if (!groupName.equals("")) {
            int groupsLength = groups.size();
            for (int i = 0; i < groupsLength; i++) {
                if (groups.get(i).getGroupName().equals(groupName)) {
                    messenger.nameIsBusy(groupName);
                    return;
                }
            }
            groups.add(new Group(groupName));
            messenger.createSuccess(groupName);
        } else {
            messenger.emptyName(groupName);
        }
    }

    @Override
    public Group search() {
        messenger.nameRequest("group");
        String groupName = scanner.nextLine();
        System.out.println(groupName + ":\n");
        int length = groups.size();
        for (int i = 0; i < length; i++) {
            if (groups.get(i).getGroupName().equals(groupName)) {
                return groups.get(i);
            }
        }
        messenger.nameNonexistent(groupName);
        return new Group("");
    }

    @Override
    public void update() {
        messenger.nameRequest("group");
        String groupName = scanner.nextLine();
        int groupLength = groups.size();
        for (int i = 0; i < groupLength; i++) {
            if (groups.get(i).getGroupName().equals(groupName)) {
                messenger.newNameRequest("contact");
                String newGroupName = scanner.nextLine();
                groups.get(i).setName(newGroupName);
                int contactsLength = contacts.size();
                for (int j = 0; j < contactsLength; j++) {
                    if (contacts.get(j).getContactGroups() != null) {
                        int contactGroupsLength = contacts.get(j).getContactGroups().size();
                        for (int k = 0; k < contactGroupsLength; k++) {
                            if (contacts.get(j).getContactGroups().get(k).equals(groupName)) {
                                contacts.get(j).getContactGroups().remove(k);
                                contacts.get(j).getContactGroups().add(newGroupName);
                                return;
                            }
                        }
                    }
                }
            }
        }
        messenger.nameNonexistent(groupName);
    }

    @Override
    public void delete() {
        messenger.nameRequest("group");
        String groupName = scanner.nextLine();
        int groupLength = groups.size();
        for (int i = 0; i < groupLength; i++) {
            if (groups.get(i).getGroupName().equals(groupName)) {
                int groupContactsLength = groups.get(i).getGroupContacts().size();
                for (int j = 0; j < groupContactsLength; j++) {
                    int contactGroupsLength = groups.get(i).getGroupContacts().get(j).getContactGroups().size();
                    for (int k = 0; k < contactGroupsLength; k++) {
                        if (groups.get(i).getGroupContacts().get(j).getContactGroups().get(k).equals(groupName)) {
                            groups.get(i).getGroupContacts().get(j).getContactGroups().remove(k);
                            groups.remove(i);
                            messenger.removeSuccess(groupName);
                            return;
                        }
                    }
                }
            }
        }
        messenger.nameNonexistent(groupName);
    }

    @Override
    public void check() {
        int groupsLength = groups.size();
        if (!groups.isEmpty()) {
            for (int i = 0; i < groupsLength; i++) {
                System.out.println(groups.get(i).getGroupName());
            }
        } else messenger.emptyList("Group list");
    }
}
