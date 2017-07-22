package ru.handbook.dao;

import ru.handbook.core.SystemMessages;

/**
 * Created by asus on 16.07.2017.
 */
public interface ObjectDAO<T> {

    SystemMessages messenger = new SystemMessages();

    /**
     * <p>Creating object</p>
     */
    void create();

    /**
     * <p>Searching object</p>
     *
     * @return T
     */
    T search();

    /**
     * <p>Updating fields of object</p>
     */
    void update() throws CloneNotSupportedException;

    /**
     * <p>Deleting object</p>
     */
    void delete() throws CloneNotSupportedException;

    /**
     * <p>Looking for objects same category</p>
     */
    void check();
}
