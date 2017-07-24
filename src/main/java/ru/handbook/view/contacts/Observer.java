package ru.handbook.view.contacts;

import ru.handbook.model.product.Contact;

import java.util.List;

public interface Observer {

    void handleEvent(List<Contact> contacts);
}
