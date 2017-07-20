package ru.handbook.controller;

import ru.handbook.dao.GroupDAOImpl;

public class GroupControllerDAOImpl implements ControllerDAO {
    GroupDAOImpl groupDAO = new GroupDAOImpl();
    public void search() {
        groupDAO.search().getGroupInfo();
    }

    public void create() {
        groupDAO.create();
    }

    public void check() {
        groupDAO.check();
    }

    public void delete() {
        groupDAO.delete();
    }

    public void update() {
        groupDAO.update();
    }
}
