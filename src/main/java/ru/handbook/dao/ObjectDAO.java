package ru.handbook.dao;

import ru.handbook.core.SystemMessages;

/**
 * Created by asus on 16.07.2017.
 */
public interface ObjectDAO<T> {

    SystemMessages messenger = new SystemMessages();

    /**
     * <p>Создание сущности</p>
     */
    void create();

    /**
     * <p>Поиск сущности</p>
     *
     * @return T возвращает ссылку на искомую сущность
     */
    T search();

    /**
     * <p>Обновить поля сущности, используется клонирование при поиске</p>
     */
    void update() throws CloneNotSupportedException;

    /**
     * <p>Удаление сущности, используется клонирование при поиске</p>
     */
    void delete() throws CloneNotSupportedException;

    /**
     * <p>Просмотр всех сущностей данного типа</p>
     */
    void check();
}
