package ru.handbook.controller;
import ru.handbook.core.SystemMessages;
/**
 * Created by asus on 16.07.2017.
 */
public interface HandbookActions<T> {
    SystemMessages messenger = new SystemMessages();

    /**
     * <p>Creating object</p>
     */
    public void create();
    /**
     * <p>Searching object</p>
     * @return T
     */
    public T search();
    /**
     * <p>Updating fields of object</p>
     */
    public void update();
    /**
     * <p>Deleting object</p>
     */
    public void delete();
    /**
     * <p>Looking for objects same category</p>
     */
    public void check();
}
