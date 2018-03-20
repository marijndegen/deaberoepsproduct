package nl.han.dea.marijn.models;

import nl.han.dea.marijn.database.config.JDBC;
import org.junit.Before;
import org.junit.Test;

public interface CRUDModel {

    void create();

    void read();

    void update();

    void delete();
}
