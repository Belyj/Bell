package ru.handbook.model;

import ru.handbook.core.contactview.Observer;

public interface Observable {
    void addObserver(Observer observer);

    void removeObserver(Observer observer);

    void notifyObservers();

}
