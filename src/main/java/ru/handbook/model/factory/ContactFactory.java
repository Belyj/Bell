package ru.handbook.model.factory;

import ru.handbook.dao.ContactDAOImpl;
import ru.handbook.model.product.Contact;
import ru.handbook.model.HandbookDataStorage;
import static ru.handbook.dao.ObjectDAO.messenger;

/**
 * Created by operator1 on 24.07.2017.
 */
public class ContactFactory implements Factory {

    HandbookDataStorage dataStorage = HandbookDataStorage.getInstance();
    //ContactDAOImpl contactDAO = new ContactDAOImpl();

    public Contact create(String name) {
        if (!name.isEmpty()) {
            if (new ContactDAOImpl().isContactNameExist(dataStorage.getContacts(), name)) {
                messenger.nameIsBusy(name);
                return null;
            }
            return new Contact(name);
        } else {
            messenger.emptyName(name);
            return null;
        }
    }
}
