package ru.handbook.model;

import ru.handbook.view.contacts.Observer;

import java.io.Serializable;
/*
 Стандартный интерфейс для издателя
 */
public interface Observable extends Serializable {

    /*
    <p>Добавление нового подписчика</p>
    @param Observer эземпляр наблюдателя
     */
    void addObserver(Observer observer);

    /*
    <p>Удаление подписчика</p>
    @param Observer эземпляр наблюдателя
     */
    void removeObserver(Observer observer);

    /*
    <p>Рассылка подписчикам</p>
     */
    void notifyObservers();

}
