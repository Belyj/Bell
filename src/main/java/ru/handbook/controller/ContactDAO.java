package ru.handbook.controller;

import ru.handbook.model.Contact;

import static ru.handbook.core.Main.contacts;
import static ru.handbook.core.Main.groups;
import static ru.handbook.core.Main.scanner;

/**
 * Created by operator1 on 19.07.2017.
 */
public interface ContactDAO extends ObjectDAO {
    void create();

    Contact search();

    void update();

    void delete();

    void check();
}
