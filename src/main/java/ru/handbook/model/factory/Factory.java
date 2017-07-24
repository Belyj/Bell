package ru.handbook.model.factory;

import ru.handbook.model.product.HandbookObject;

/**
 * Created by operator1 on 24.07.2017.
 */
public interface Factory {

    /**
     * <p>Создание сущности</p>
     * @return HandbookObject сущность
     * @param String name рпинимает на вход имя создаваемой сущности
     */
    HandbookObject create(String name);
}
