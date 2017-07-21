package ru.handbook.core;

import ru.handbook.controller.MenuControllerDAOImpl;
import ru.handbook.exceptions.NotCorrectCommandException;

import static ru.handbook.core.Main.flag;
import static ru.handbook.core.Main.scanner;

/**
 * Created by operator1 on 12.07.2017.
 */
public class Menu {

    MenuControllerDAOImpl menuController = new MenuControllerDAOImpl();

    public Menu() {
        int command;
        while (flag) {
            System.out.println("Entery #comand");
            System.out.println("0: searchContact\n " +
                    "1: createContact\n " +
                    "2: changeContact\n " +
                    "3: deleteContact\n " +
                    "4: searchGroup\n " +
                    "5: createGroup\n " +
                    "6: addInGroup\n " +
                    "7: deleteFromGroup\n " +
                    "8: checkContacts\n " +
                    "9: checkGroups\n " +
                    "10: deleteGroup\n " +
                    "11: updateGroup\n " +
                    "12: exitProgram");
            if (scanner.hasNextInt()) {
                command = Integer.parseInt(scanner.nextLine());
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
                        menuController.removeFromGroup();
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
            } else throw new NotCorrectCommandException("Command must be integer");
        }
    }
}