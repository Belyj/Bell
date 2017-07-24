package ru.handbook.model.product;

import java.io.Serializable;
/*
Интерфейс для сущностей программы. Поддерживает сериализауию и переопределяет клонирование.
 */
public interface HandbookObject extends Serializable, Cloneable {

    /*
    <p>Поменять имя</p>
    @param новое имя
     */
    void setName(String name);

    /*
    <p>Взять имя</p>
    @return String имя объекта
     */
    String getName();

    /*
    <p>Метод клонирования</p>
    @return HandbookObject возвращает точную копию объекта
     */
    HandbookObject clone() throws CloneNotSupportedException;
}
