package ru.handbook.model.factory;

import ru.handbook.dao.GroupDAOImpl;
import ru.handbook.model.product.Group;
import ru.handbook.model.HandbookDataStorage;
import static ru.handbook.dao.ObjectDAO.messenger;

/**
 * Created by operator1 on 24.07.2017.
 */
public class GroupFactory implements Factory {

    HandbookDataStorage dataStorage = HandbookDataStorage.getInstance();
    //GroupDAOImpl groupDAO = new GroupDAOImpl();

    public Group create(String name) {
        if (!name.isEmpty()) {
            if (new GroupDAOImpl().isGroupNameExist(dataStorage.getGroups(), name)) {
                messenger.nameIsBusy(name);
                return null;
            }
            return new Group(name);
        } else {
            messenger.emptyName(name);
            return null;
        }
    }
}
