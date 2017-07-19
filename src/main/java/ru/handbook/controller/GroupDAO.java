package ru.handbook.controller;

import ru.handbook.model.Group;

import static ru.handbook.core.Main.contacts;
import static ru.handbook.core.Main.groups;
import static ru.handbook.core.Main.scanner;

/**
 * Created by operator1 on 19.07.2017.
 */
public interface GroupDAO extends ObjectDAO {
    @Override
    void create();

    @Override
    Object search();

    @Override
    void update();

    @Override
    void delete();

    @Override
    void check();
}
