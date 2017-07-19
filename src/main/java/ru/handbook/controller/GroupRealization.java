package ru.handbook.controller;

import ru.handbook.model.Group;

import static ru.handbook.core.Main.contacts;
import static ru.handbook.core.Main.groups;
import static ru.handbook.core.Main.scanner;

/**
 * Created by asus on 16.07.2017.
 */
public class GroupRealization extends GroupDAO {
    @Override
    public void create() {
        messenger.nameRequest("group");
        super.create();
    }

    @Override
    public Group search() {
        messenger.nameRequest("group");
        return super.search();
    }

    @Override
    public void update() {
        messenger.nameRequest("group");
        super.update();
    }

    @Override
    public void delete() {
        messenger.nameRequest("group");
        super.delete();
    }

    @Override
    public void check() {
        super.check();
    }
}
