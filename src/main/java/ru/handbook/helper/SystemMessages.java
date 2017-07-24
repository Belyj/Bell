package ru.handbook.helper;

/**
 * Created by operator1 on 14.07.2017.
 */
public class SystemMessages {

    final public void nameRequest(String s) {
        System.out.println("Entery " + s + " name");
    }

    final public void newNameRequest(String s) {
        System.out.println("Entery new " + s + " name");
    }

    final public void nameNonexistent(String name) {
        System.out.println(name + " does not exist");
    }

    final public void nameIsBusy(String name) {
        System.out.println("Name " + name + " is busy");
    }

    final public void createSuccess(String name) {
        System.out.println(name + " was created");
    }

    final public void removeSuccess(String name) {
        System.out.println(name + " was removed");
    }

    final public void updateSuccess(String name) {
        System.out.println("Group " + name + " was update");
    }

    final public void emptyName(String name) {
        System.out.println(name + " can not be empty");
    }

    final public void emptyList(String s) {
        System.out.println(s + " is empty");
    }

    final public void newDataRequest(String data) {
        System.out.println("Entery new " + data);
    }

    final public void addGroupSuccess(String contactName, String groupName) {
        System.out.println(contactName + " was add to " + groupName + " group");
    }

    final public void removeGroupSuccess(String contactName, String groupName) {
        System.out.println(contactName + " was remove from " + groupName + " group");
    }
}
