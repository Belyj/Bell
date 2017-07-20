package ru.handbook.controller;

import ru.handbook.dao.ObjectDAO;

public interface ControllerDAO {
    void search();

    void create();

    void update();

    void delete();

    void check();
}
