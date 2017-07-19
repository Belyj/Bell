package ru.handbook.dataBaseWorking.serialization;

import java.io.*;

import static ru.handbook.core.Main.contacts;
import static ru.handbook.core.Main.groups;

/**
 * Created by asus on 16.07.2017.
 */
public class Deserializer {
    public static void deSerialize() {
        ObjectInputStream objectInputStream = createOIS();
        try {
            if (new File("temp.out").exists()) {
                System.out.println("File founded");
                Serial serial = (Serial) objectInputStream.readObject();
                contacts = serial.getContacts();
                groups = serial.getGroups();
                System.out.println("Read file success");
                return;
            }
        } catch (IOException e) {
            System.out.println("File for deserializing not found");
        } catch (ClassNotFoundException e) {
            System.out.println("Handbook have not component for reading file");
        } finally {
            if (new File("temp.out").exists()) {
                try {
                    objectInputStream.close();
                    return;
                } catch (IOException e) {
                    System.out.println("Inputstream didnot created");
                    return;
                }
            } else System.out.println("Inputstream didnot created");
        }
    }

    public static ObjectInputStream createOIS() {
        try {
            //String path = new File("").getAbsolutePath();
            if (new File("temp.out").exists()) {
                System.out.println("Creating ObjectInputStream...");
                return new ObjectInputStream(createFIS());
            } else System.out.println("File does not exist");
        } catch (IOException e) {
            System.out.println("File for serializing not found");
        }
        return null;
    }

    public static FileInputStream createFIS() {
        try {
            System.out.println("Creating FileInputStream...");
            return new FileInputStream("temp.out");
        } catch (FileNotFoundException e) {

        }
        System.out.println("File not found");
        return null;
    }
}
