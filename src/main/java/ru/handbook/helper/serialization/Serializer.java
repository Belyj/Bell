package ru.handbook.helper.serialization;

import ru.handbook.model.HandbookDataStorage;

import java.io.*;

/**
 * Created by asus on 15.07.2017.
 */
public class Serializer {

    public static void serialize() {
        ObjectOutputStream objectOutputStream = createOOS();
        try {
            objectOutputStream.writeObject(HandbookDataStorage.getInstance());
            System.out.println("Serializing is success");
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                objectOutputStream.close();
            } catch (IOException e) {
                System.out.println("OutputStream was not created");
            }
        }
    }

    private static ObjectOutputStream createOOS() {
        try {
//             if (new File("temp.out").exists()) {
            System.out.println("Creating ObjectOutputStream...");
            return new ObjectOutputStream(createFOS());
//            }  else System.out.println("File does not exist");
        } catch (IOException e) {
            createFile();
        }
        return null;
    }

    private static FileOutputStream createFOS() {
        try {
            System.out.println("Creating FileOutputStream...");
            return new FileOutputStream("temp.out");
        } catch (FileNotFoundException e) {
            createFile();
        }
        System.out.println("File not found");
        return null;
    }


    private static File createFile() {
        String path = new File("").getAbsolutePath();
        System.out.println("Creating file for serialization...");
        File file = new File(path + "temp.out");
        return file;
    }
}
