package ru.handbook.core.contactview;

import ru.handbook.model.Contact;
import ru.handbook.model.HandbookDataStorage;

import javax.swing.*;
import java.util.List;

public class ViewContactSwing extends JFrame implements Observer {

    private JTextArea textArea;

    public ViewContactSwing() {
        setTitle("Contact view");
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setBounds(400, 100, 500, 500);
        textArea = new JTextArea(2, 5);
        textArea.setEditable(false);
        textArea.setLineWrap(true);
        this.add(textArea);
        this.setVisible(true);
        HandbookDataStorage.getInstance().addObserver(this);
    }

    public void handleEvent(List<Contact> contacts) {
        for (Contact contact : contacts) {
            this.textArea.setText(this.textArea.getText() + "\n" + contact.getContactName());
        }
    }
}