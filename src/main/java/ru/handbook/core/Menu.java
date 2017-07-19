package ru.handbook.core;

import static ru.handbook.core.Main.flag;
import java.io.Serializable;
import ru.handbook.controller.MenuController;

/**
 * Created by operator1 on 12.07.2017.
 */
public class Menu implements Serializable {
    MenuController menuController = new MenuController();
    public void command(int command) {
        switch (command) {
            case (0):
                menuController.searchContact();
                break;
            case (1):
                menuController.createContact();
                break;
            case (2):
                menuController.updateContact();
                break;
            case (3):
                menuController.deleteContact();
                break;
            case (4):
                menuController.searchGroup();
                break;
            case (5):
                menuController.createGroup();
                break;
            case (6):
                menuController.addInGroup();
                break;
            case (7):
                menuController.deleteFromGroup();
                break;
            case (8):
                menuController.checkContacts();
                break;
            case (9):
                menuController.checkGroups();
                break;
            case (10):
                menuController.deleteGroup();
                break;
            case (11):
                menuController.updateGroup();
                break;
            case (12):
                System.out.println("Exit from program");
                flag = false;
                break;
            default:
                System.out.println("Handbook have not command: " + command);
        }
    }
}