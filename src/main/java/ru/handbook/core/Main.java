package ru.handbook.core;

import ru.handbook.core.contactview.ViewContactSwing;
import ru.handbook.exceptions.*;

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
        new ViewContactSwing();
        new Menu();
        serialize();
    }
}
