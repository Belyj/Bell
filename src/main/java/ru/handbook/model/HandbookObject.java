package ru.handbook.model;

import ru.handbook.core.contactview.Observer;

import java.io.Serializable;

public interface HandbookObject extends Serializable, Cloneable {

    void setName(String name);

    String getName();
}
