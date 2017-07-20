package ru.handbook.core;

import ru.handbook.exceptions.*;
import ru.handbook.model.Contact;
import ru.handbook.model.Group;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static ru.handbook.serialization.Deserializer.deSerialize;
import static ru.handbook.serialization.Serializer.*;

/**
 * Created by asus on 11.07.2017.
 */
public class Main {
    static Menu menu = new Menu();
    public static boolean flag = true;
    public static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        deSerialize();
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
                menu.command(Integer.parseInt(scanner.nextLine()));
            } else throw new NotCorrectCommandException("Command must be integer");
        }
        serialize();
    }
}
