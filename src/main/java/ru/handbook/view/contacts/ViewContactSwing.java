package ru.handbook.view.contacts;

import ru.handbook.model.Contact;
import ru.handbook.model.HandbookDataStorage;

import javax.swing.*;
import java.util.List;

public class ViewContactSwing extends JFrame implements Observer {

    private JTextArea view;

    public ViewContactSwing() {
        setTitle("Contact view");
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setBounds(400, 100, 500, 500);
        view = new JTextArea();
        view.setEditable(false);
        view.setLineWrap(true);
        this.add(view);
        this.setVisible(true);
        HandbookDataStorage.getInstance().addObserver(this);
    }

    public void handleEvent(List<Contact> contacts) {
        view.repaint();
        for (Contact contact : contacts) {
            this.view.setText(this.view.getText() + "\n" + contact.getName());
        }
    }
}