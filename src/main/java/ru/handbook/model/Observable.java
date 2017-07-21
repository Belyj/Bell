package ru.handbook.model;

import ru.handbook.core.contactview.Observer;

import java.io.Serializable;

public interface Observable extends Serializable {

    void addObserver(Observer observer);

    void removeObserver(Observer observer);

    void notifyObservers();

}
