package ru.handbook.controller;

import ru.handbook.model.Contact;

import static ru.handbook.core.Main.contacts;
import static ru.handbook.core.Main.groups;
import static ru.handbook.core.Main.scanner;

/**
 * Created by asus on 16.07.2017.
 */
public class ContactRealization extends ContactDAO {
    @Override
    public void create() {
        messenger.nameRequest("contact");
        super.create();
    }

    @Override
    public Contact search() {
        messenger.nameRequest("contact");
        return super.search();
    }

    @Override
    public void update() {
        messenger.nameRequest("contact");
        super.update();
    }

    @Override
    public void delete() {
        messenger.nameRequest("contact");
        super.delete();
    }

    @Override
    public void addInGroup() {
        messenger.nameRequest("contact");
        super.addInGroup();
    }

    @Override
    public void removeFromGroup() {
        messenger.nameRequest("contact");
        super.removeFromGroup();
    }

    @Override
    public void check() {
        super.check();
    }
}
