package ru.handbook.dataBaseWorking;

import ru.handbook.dataBaseWorking.serialization.SerialDAO;
import ru.handbook.dataBaseWorking.serialization.SerialDAOFactory;

/**
 * Created by operator1 on 19.07.2017.
 */
public abstract class DAOFactory {
    public static final int SERIAL = 1;

    public abstract SerialDAO getSerialDAO();

    public static DAOFactory getDAOFactory(
            int whichFactory) {

        switch (whichFactory) {
            case SERIAL:
                return new SerialDAOFactory();
            default:
                return null;
        }
    }
}

